/**
 *  Copyright 2015 SmartThings and 2016 Robert Vandervoort
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Aeon Labs DSB04100-ZWUS - Z-Wave Door & Window Sensor
 *
 *  Author: SmartThings and Robert Vandervoort
 *  Date: 7-17-2016
 */

metadata {
	definition (name: "Aeon Labs DSB04100-ZWUS - Z-Wave Door & Window Sensor - RV", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Contact Sensor"
		capability "Tamper Alert"
		capability "Sensor"
		capability "Battery"
		capability "Configuration"
		// RAW ID 0 0 0x0701 0 0 0 f 0x5E 0x86 0x72 0x85 0x59 0x73 0x71 0x84 0x30 0x80 0x70 0x98 0x7A 0xEF 0x5A
		fingerprint deviceId: "0x0701", inClusters: "0x5E,0x86,0x72,0x85,0x59,0x73,0x71,0x84,0x30,0x80,0x70,0x98,0x7A,0xEF,0x5A"
	}

	simulator {
		// status messages
		status "open":  "command: 2001, payload: FF"
		status "closed": "command: 2001, payload: 00"
		status "wake up": "command: 8407, payload: "
		status "detected": "command: 7105, payload:  000000FF07030000"
		status "clear": "command: 7105, payload:  000000FF07000000"
	}

	tiles {
		standardTile("contact", "device.contact", width: 2, height: 2) {
			state "open", label: '${name}', icon: "st.contact.contact.open", backgroundColor: "#ffa81e"
			state "closed", label: '${name}', icon: "st.contact.contact.closed", backgroundColor: "#79b821"
		}
		standardTile("tamper", "device.tamper", width: 2, height: 2) {
			state "detected", label: '${currentValue}', icon: "st.motion.acceleration.active", backgroundColor: "#ff0000"
			state "clear", label: '${currentValue}', icon: "st.motion.acceleration.inactive", backgroundColor: "#00ff00"
		}
		valueTile("battery", "device.battery", inactiveLabel: false, decoration: "flat") {
			state "battery", label:'${currentValue}% battery', unit:""
		}
		main "contact"
		details(["contact", "tamper", "battery"])
	}
}

def parse(String description) {
	def result = null
	if (description.startsWith("Err 106")) {
		if (state.sec) {
			log.debug description
		} else {
			result = createEvent(
				descriptionText: "This sensor failed to complete the network security key exchange. If you are unable to control it via SmartThings, you must remove it from your network and add it again.",
				eventType: "ALERT",
				name: "secureInclusion",
				value: "failed",
				isStateChange: true,
			)
		}
	} else if (description != "updated") {
		def cmd = zwave.parse(description, [0x20: 1, 0x25: 1, 0x30: 1, 0x31: 5, 0x80: 1, 0x84: 1, 0x71: 3, 0x9C: 1])
		if (cmd) {
			result = zwaveEvent(cmd)
		}
	}
	log.debug "parsed '$description' to $result"
	return result
}

def updated() {
	def cmds = []
	if (!state.MSR) {
		cmds = [
			command(zwave.manufacturerSpecificV2.manufacturerSpecificGet()),
			"delay 1200",
			zwave.wakeUpV1.wakeUpNoMoreInformation().format()
		]
	} else if (!state.lastbat) {
		cmds = []
	} else {
		cmds = [zwave.wakeUpV1.wakeUpNoMoreInformation().format()]
	}
	response(cmds)
}

def configure() {
	commands([
		zwave.manufacturerSpecificV2.manufacturerSpecificGet(),
		zwave.batteryV1.batteryGet()
	], 6000)
}

def sensorValueEvent(value) {
	if (value) {
		createEvent(name: "contact", value: "open", descriptionText: "$device.displayName is open")
	} else {
		createEvent(name: "contact", value: "closed", descriptionText: "$device.displayName is closed")
	}
	result
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd)
{
	sensorValueEvent(cmd.value)
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd)
{
	sensorValueEvent(cmd.value)
}

def zwaveEvent(physicalgraph.zwave.commands.notificationv3.NotificationReport cmd)
{
	def result = []
	if (cmd.notificationType == 0x07) {
		if (cmd.event == 0x03) {
			result << createEvent(name: "tamper", value: "detected", descriptionText: "$device.displayName was removed.", isStateChange: true)
			if(!state.MSR) result << response(command(zwave.manufacturerSpecificV2.manufacturerSpecificGet()))
		} else if (cmd.event == 0x00) {
			result << createEvent(name: "tamper", value: "clear", descriptionText: "$device.displayName was replaced.", isStateChange: true)
			if(!state.MSR) result << response(command(zwave.manufacturerSpecificV2.manufacturerSpecificGet()))
		} else if (cmd.notificationType == 0x06 && cmd.event == 0x16) {
			result << sensorValueEvent(1)
		} else if (cmd.notificationType == 0x06 && cmd.event == 0x17) {
			result << sensorValueEvent(0)
		}
	result
	}
	result
}

def zwaveEvent(physicalgraph.zwave.commands.wakeupv1.WakeUpNotification cmd)
{
	def event = createEvent(descriptionText: "${device.displayName} woke up", isStateChange: false)
	def cmds = []
	if (!state.MSR) {
		cmds << command(zwave.manufacturerSpecificV2.manufacturerSpecificGet())
		cmds << "delay 1200"
	}
	if (!state.lastbat || now() - state.lastbat > 53*60*60*1000) {
		cmds << command(zwave.batteryV1.batteryGet())
	} else {
		cmds << zwave.wakeUpV1.wakeUpNoMoreInformation().format()
	}
	[event, response(cmds)]
}

def zwaveEvent(physicalgraph.zwave.commands.batteryv1.BatteryReport cmd) {
	def map = [ name: "battery", unit: "%" ]
	if (cmd.batteryLevel == 0xFF) {
		map.value = 1
		map.descriptionText = "${device.displayName} has a low battery"
		map.isStateChange = true
	} else {
		map.value = cmd.batteryLevel
	}
	state.lastbat = now()
	[createEvent(map), response(zwave.wakeUpV1.wakeUpNoMoreInformation())]
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
	def result = []
	def msr = String.format("%04X-%04X-%04X", cmd.manufacturerId, cmd.productTypeId, cmd.productId)
	log.debug "msr: $msr"
	updateDataValue("MSR", msr)
	result << createEvent(descriptionText: "$device.displayName MSR: $msr", isStateChange: false)
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x20: 1, 0x25: 1, 0x30: 1, 0x31: 5, 0x80: 1, 0x84: 1, 0x71: 3, 0x9C: 1])
	// log.debug "encapsulated: $encapsulatedCommand"
	if (encapsulatedCommand) {
		state.sec = 1
		zwaveEvent(encapsulatedCommand)
	}
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	createEvent(descriptionText: "$device.displayName: $cmd", displayed: false)
}

private command(physicalgraph.zwave.Command cmd) {
	if (state.sec == 1) {
		zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
	} else {
		cmd.format()
	}
}

private commands(commands, delay=200) {
	delayBetween(commands.collect{ command(it) }, delay)
}