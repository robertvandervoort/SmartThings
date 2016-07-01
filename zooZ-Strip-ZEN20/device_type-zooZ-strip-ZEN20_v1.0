/**
 *  Derived from Aeon Smart Strip code Copyright 2015 SmartThings
 *	reworked 6/2016 by Robert Vandervoort
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
 */
metadata {
	definition (name: "zooZ ZEN20 - Z-Wave plus power strip", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Switch"
		capability "Refresh"
		capability "Configuration"
		capability "Actuator"
        capability "Zw Multichannel"

		(1..5).each { n ->
			attribute "switch$n", "enum", ["on", "off"]
			command "on$n"
			command "off$n"
		}
        //RAW DESC 0 0 0x1004 0 0 0 b 0x5E 0x85 0x59 0x5A 0x72 0x60 0x8E 0x73 0x27 0x25 0x86
		// Device reset locally 0x5A, multi channel assoc 0x8E, powerlevel 0x73
		fingerprint deviceId: "0x1004", inClusters: "0x5E,0x85,0x59,0x5A,0x72,0x60,0x8E,0x73,0x27,0x25,0x86"
	}

	// simulator metadata
	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off":  "command: 2003, payload: 00"
		status "switch1 on": "command: 600D, payload: 01 00 25 03 FF"
		status "switch1 off": "command: 600D, payload: 01 00 25 03 00"
		status "switch5 on": "command: 600D, payload: 05 00 25 03 FF"
		status "switch5 off": "command: 600D, payload: 05 00 25 03 00"
		// reply messages
		reply "2001FF,delay 100,2502": "command: 2503, payload: FF"
		reply "200100,delay 100,2502": "command: 2503, payload: 00"
	}

	// tile definitions
	tiles {
    	(1..5).each { n ->
			standardTile("switch$n", "switch$n", width: 1, height: 1, canChangeIcon: true) {
				state "on", label: '${name}', action: "off$n", icon: "st.switches.switch.on", backgroundColor: "#79b821"
				state "off", label: '${name}', action: "on$n", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			}
        	valueTile("label$n", "socket $n", width: 2, height: 1, decoration: "flat") {
           		state "default", label: "$n", defaultState: true
        	}
        }
		standardTile("refresh", "device.power", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}
		main(["switch1"])
		details(["switch1", "label1", "switch2", "label2", "switch3", "label3", "switch4", "label4", "switch5", "label5"])
	}
}


def parse(String description) {
	def result = null
	if (description.startsWith("Err")) {
		result = createEvent(descriptionText:description, isStateChange:true)
	} else if (description != "updated") {
		def cmd = zwave.parse(description, [0x60: 3, 0x32: 3, 0x25: 1, 0x20: 1])
		if (cmd) {
			result = zwaveEvent(cmd, null)
		}
	}
	log.debug "parsed '${description}' to ${result.inspect()}"
	result
}

def endpointEvent(endpoint, map) {
	if (endpoint) {
		map.name = map.name + endpoint.toString()
	}
	createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd, endpoint) {
	def map = [name: "switch", type: "physical", value: (cmd.value ? "on" : "off")]
	def events = [endpointEvent(endpoint, map)]
	def cmds = []
	if (endpoint) {
		cmds += delayBetween([2,0].collect { s -> encap(zwave.meterV3.meterGet(scale: s), endpoint) }, 1000)
		if(endpoint < 5) cmds += ["delay 1500", encap(zwave.basicV1.basicGet(), endpoint + 1)]
	} else if (events[0].isStateChange) {
		events += (1..5).collect { ep -> endpointEvent(ep, map.clone()) }
		cmds << "delay 3000"
		cmds += delayBetween((0..5).collect { ep -> encap(zwave.meterV3.meterGet(scale: 2), ep) }, 800)
	}
	if(cmds) events << response(cmds)
	events
}

def zwaveEvent(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinaryReport cmd, endpoint) {
	def map = [name: "switch", value: (cmd.value ? "on" : "off")]
	def events = [endpointEvent(endpoint, map)]
	def cmds = []
	if (!endpoint && events[0].isStateChange) {
		events += (1..5).collect { ep -> endpointEvent(ep, map.clone()) }
	}
	events
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd, ep) {
	updateDataValue("MSR", String.format("%04X-%04X-%04X", cmd.manufacturerId, cmd.productTypeId, cmd.productId))
	null
}

def zwaveEvent(physicalgraph.zwave.Command cmd, ep) {
	log.debug "${device.displayName}: Unhandled ${cmd}" + (ep ? " from endpoint $ep" : "")
}

def onOffCmd(value, endpoint = null) {
	[
		encap(zwave.basicV1.basicSet(value: value), endpoint),
		"delay 500",
		encap(zwave.switchBinaryV1.switchBinaryGet(), endpoint)
	]
}

def on() { onOffCmd(0xFF) }
def off() { onOffCmd(0x0) }

def on1() { onOffCmd(0xFF, 1) }
def on2() { onOffCmd(0xFF, 2) }
def on3() { onOffCmd(0xFF, 3) }
def on4() { onOffCmd(0xFF, 4) }
def on5() { onOffCmd(0xFF, 5) }

def off1() { onOffCmd(0, 1) }
def off2() { onOffCmd(0, 2) }
def off3() { onOffCmd(0, 3) }
def off4() { onOffCmd(0, 4) }
def off5() { onOffCmd(0, 5) }

def refresh() {
	delayBetween([
		zwave.basicV1.basicGet().format(),
		encap(zwave.basicV1.basicGet(), 1)  // further gets are sent from the basic report handler
	])
}

def configure() {
	def cmds = [
		//zwave.configurationV1.configurationSet(parameterNumber: 101, size: 4, configurationValue: [0, 0, 0, 1]).format(),
		//zwave.configurationV1.configurationSet(parameterNumber: 102, size: 4, configurationValue: [0, 0, 0x79, 0]).format(),
		//zwave.configurationV1.configurationSet(parameterNumber: 112, size: 4, scaledConfigurationValue: 90).format(),
	]
	[5, 8, 9, 10, 11].each { p ->
		cmds << zwave.configurationV1.configurationSet(parameterNumber: p, size: 2, scaledConfigurationValue: 5).format()
	}
	[12, 15, 16, 17, 18].each { p ->
		cmds << zwave.configurationV1.configurationSet(parameterNumber: p, size: 1, scaledConfigurationValue: 50).format()
	}
	cmds += [
		//zwave.configurationV1.configurationSet(parameterNumber: 111, size: 4, scaledConfigurationValue: 15*60).format(),
		//zwave.configurationV1.configurationSet(parameterNumber: 4, size: 1, configurationValue: [1]).format(),
	]
	delayBetween(cmds) + "delay 5000" + refresh()
}

private encap(cmd, endpoint) {
	if (endpoint) {
		zwave.multiChannelV3.multiChannelCmdEncap(destinationEndPoint:endpoint).encapsulate(cmd).format()
	} else {
		cmd.format()
	}
}