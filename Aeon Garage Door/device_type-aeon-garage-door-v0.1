/*
 * V 0.1 of Aeon Garage Door code 12/15/2015
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 * some code used from various SmartThings device type and metering code from ElasticDev
 *
 * change log:
 * v 0.1
*/

 metadata {
	definition (name: "Aeon Garage Door - RV v0.1", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Alarm" // 0x71 Notification v4
		capability "Battery"
		capability "Switch" // 0x25 Switch Binary v1
		capability "Configuration" // 0x70 Configuration v1
		capability "Actuator"
		capability "Door Control"
		capability "Garage Door Control"
		capability "Contact Sensor"
		capability "Refresh"
		capability "Sensor"
		
		command "calibrate"
		
		/*
		0x22 Application Status v1
		0x5E Zwave Gen 5 v2
		0x85 Association v2
		0x59 Association Grp Info v1
		0x72 Manufacturer Specific v2
		0x86 Version v1
		0x7A Firmware Update Md v2
		0x73 Powerlevel v1
		0x98 Security v1
		0x66 BARRIER_OPERATOR v1
		0xEF Mark v1
		0x5A Device Reset Locally v1
		0x82 Hail v1
		*/
		
		// Raw description
		// 0 0 0x4007 0 0 0 10 0x5E 0x25 0x70 0x85 0x59 0x72 0x86 0x7A 0x73 0x98 0x66  0xEF 0x22 0x5A 0x82 
		fingerprint deviceId: "0x10", inClusters: "0x98"
		fingerprint inClusters: "0x5E, 0x25, 0x70, 0x85, 0x59, 0x72, 0x86, 0x7A, 0x73, 0x98, 0x66, 0x71, 0xEF, 0x22, 0x5A", outClusters: "0x82"
	}
	// simulator metadata
	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off": "command: 2003, payload: 00"

		// reply messages
		reply "2001FF,delay 100,2502": "command: 2503, payload: FF"
		reply "200100,delay 100,2502": "command: 2503, payload: 00"

	}

	// tile definitions
	tiles (scale: 2) {
    	multiAttributeTile(name:"main", type:"generic", width:6, height:4, canChangeIcon: true) {
			tileAttribute("device.door", key: "PRIMARY_CONTROL") {
				attributeState "unknown", label:'${name}', action:"refresh.refresh", icon:"st.doors.garage.garage-open", backgroundColor:"#ffa81e"
				attributeState "closed", label:'${name}', action:"door control.open", icon:"st.doors.garage.garage-closed", backgroundColor:"#79b821", nextState:"opening"
				attributeState "open", label:'${name}', action:"door control.close", icon:"st.doors.garage.garage-open", backgroundColor:"#ffa81e", nextState:"closing"
				attributeState "opening", label:'${name}', icon:"st.doors.garage.garage-opening", backgroundColor:"#ffe71e"
				attributeState "closing", label:'${name}', icon:"st.doors.garage.garage-closing", backgroundColor:"#ffe71e"
            }
		}
		standardTile("open", "device.door", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'open', action:"door control.open", icon:"st.doors.garage.garage-opening"
		}
		standardTile("close", "device.door", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'close', action:"door control.close", icon:"st.doors.garage.garage-closing"
		}
		standardTile("configure", "device.door", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "configure", label:'', action:"configuration.configure", icon:"st.secondary.configure"
		}
		standardTile("refresh", "device.door", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'', action:"refresh", icon:"st.secondary.refresh"
		}
		main "main"
		details(["main","open","close","refresh","configure"])
	}
	
    preferences { 
		input "debugOutput", "boolean", 
		title: "Enable debug logging?",
		defaultValue: false,
		displayDuringSetup: true
	}
}

def updated()
{
	state.debug = ("true" == debugOutput)
	if (state.sec && !isConfigured()) {
		// in case we miss the SCSR
		response(configure())
	}
}

import physicalgraph.zwave.commands.barrieroperatorv1.*

def parse(String description)
{
	def result = null
	if (description.startsWith("Err 106")) {
		state.sec = 0
		result = createEvent( name: "secureInclusion", value: "failed", isStateChange: true,
			descriptionText: "This sensor failed to complete the network security key exchange. If you are unable to control it via SmartThings, you must remove it from your network and add it again.")
	} else if (description != "updated") {
		def cmd = zwave.parse(description, [0x5E: 2, 0x25: 1, 0x70: 1, 0x85: 2, 0x59: 1, 0x72: 2, 0x86: 1, 0x7A: 2, 0x73: 1, 0x98: 1, 0x66: 1, 0x71: 4, 0xEF: 1, 0x22: 1, 0x5A: 1])
		if (cmd) {
			result = zwaveEvent(cmd)
		}
	}
	// if (state.debug) log.debug "Parsed '${description}' to ${result.inspect()}"
	return result
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x5E: 2, 0x25: 1, 0x70: 1, 0x85: 2, 0x59: 1, 0x72: 2, 0x86: 1, 0x7A: 2, 0x73: 1, 0x98: 1, 0x66: 1, 0x71: 4, 0xEF: 1, 0x22: 1, 0x5A: 1])
	state.sec = 1
	// if (state.debug) log.debug "encapsulated: ${encapsulatedCommand}"
	if (encapsulatedCommand) {
		zwaveEvent(encapsulatedCommand)
	} else {
		log.warn "Unable to extract encapsulated cmd from $cmd"
		createEvent(descriptionText: cmd.toString())
	}
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityCommandsSupportedReport cmd) {
	response(configure())
}

def zwaveEvent(physicalgraph.zwave.commands.powerlevelv1.PowerlevelTestNodeReport cmd) {
	if (state.debug) log.debug "===Power level test node report received=== ${device.displayName}: statusOfOperation: ${cmd.statusOfOperation} testFrameCount: ${cmd.testFrameCount} testNodeid: ${cmd.testNodeid}"
	def request = [
        physicalgraph.zwave.commands.powerlevelv1.PowerlevelGet()
    ]
    response(commands(request))
}

def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionCommandClassReport cmd) {
	if (state.debug) log.debug "---COMMAND CLASS VERSION REPORT V1--- ${device.displayName} has command class version: ${cmd.commandClassVersion} - payload: ${cmd.payload}"
}

def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionReport cmd) {
	def fw = "${cmd.applicationVersion}.${cmd.applicationSubVersion}"
	updateDataValue("fw", fw)
	if (state.debug) log.debug "---VERSION REPORT V1--- ${device.displayName} is running firmware version: $fw, Z-Wave version: ${cmd.zWaveProtocolVersion}.${cmd.zWaveProtocolSubVersion}"
}

def zwaveEvent(physicalgraph.zwave.commands.firmwareupdatemdv2.FirmwareMdReport cmd) {
	if (state.debug) log.debug "---FIRMWARE MD REPORT V2--- ${device.displayName} has Checksum of ${cmd.checksum} firmwareId: ${cmd.firmwareId}, manufacturerId: ${cmd.firmwareId}"
}    

def zwaveEvent(physicalgraph.zwave.commands.associationv2.AssociationReport cmd) {
    cmd.nodeId.each({log.debug "AssociationReport: '${cmd}', hub: '$zwaveHubNodeId' reports nodeId: '$it' is associated in group: '${cmd.groupingIdentifier}'"})
}

def zwaveEvent(physicalgraph.zwave.commands.hailv1.Hail cmd) {
	if (state.debug) log.debug "Hail received: ${cmd}"
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
	def result = []

	def msr = String.format("%04X-%04X-%04X", cmd.manufacturerId, cmd.productTypeId, cmd.productId)
	log.debug "msr: $msr"
	updateDataValue("MSR", msr)

	result << createEvent(descriptionText: "$device.displayName MSR: $msr", isStateChange: false)
	result
}

def zwaveEvent(physicalgraph.zwave.commands.applicationstatusv1.ApplicationBusy cmd) {
	def msg = cmd.status == 0 ? "try again later" :
	          cmd.status == 1 ? "try again in $cmd.waitTime seconds" :
	          cmd.status == 2 ? "request queued" : "sorry"
	createEvent(displayed: true, descriptionText: "$device.displayName is busy, $msg")
}

def zwaveEvent(physicalgraph.zwave.commands.applicationstatusv1.ApplicationRejectedRequest cmd) {
	createEvent(displayed: true, descriptionText: "$device.displayName rejected the last request")
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv2.ConfigurationReport cmd) {
    if (state.debug) log.debug "---CONFIGURATION REPORT V2--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
    if (state.debug) log.debug "---CONFIGURATION REPORT V1--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
}

def zwaveEvent(BarrierOperatorReport cmd) {
	def result = []
	def map = [ name: "door" ]
	switch (cmd.barrierState) {
		case BarrierOperatorReport.BARRIER_STATE_CLOSED:
			map.descriptionText = "$device.displayName door is closed"
            map.value = "closed"
			result << createEvent(name: "contact", value: "closed", displayed: false)
			break
		case BarrierOperatorReport.BARRIER_STATE_UNKNOWN_POSITION_MOVING_TO_CLOSE:
			map.descriptionText = "$device.displayName door is closing"
            map.value = "closing"
			break
		case BarrierOperatorReport.BARRIER_STATE_UNKNOWN_POSITION_STOPPED:
			map.descriptionText = "$device.displayName door state is unknown"
			map.value = "unknown"
			break
		case BarrierOperatorReport.BARRIER_STATE_UNKNOWN_POSITION_MOVING_TO_OPEN:
			map.descriptionText = "$device.displayName door is opening"
            map.value = "opening"
			result << createEvent(name: "contact", value: "open", displayed: false)
			break
		case BarrierOperatorReport.BARRIER_STATE_OPEN:
			map.descriptionText = "$device.displayName door is open"
            map.value = "open"
			result << createEvent(name: "contact", value: "open", displayed: false)
			break
	}
    if (state.debug) log.debug "---BARRIER OPERATOR REPORT--- ${map.descriptionText}"
	result + createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.notificationv3.NotificationReport cmd) {
	def result = []
	def map = [:]
	if (cmd.notificationType == 6) {
		map.displayed = true
		switch(cmd.event) {
			case 0x40:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName performing initialization process"
				} else {
					map.descriptionText = "$device.displayName initialization process complete"
				}
				break
			case 0x41:
				map.descriptionText = "$device.displayName door operation force has been exceeded"
				break
			case 0x42:
				map.descriptionText = "$device.displayName motor has exceeded operational time limit"
				break
			case 0x43:
				map.descriptionText = "$device.displayName has exceeded physical mechanical limits"
				break
			case 0x44:
				map.descriptionText = "$device.displayName unable to perform requested operation (UL requirement)"
				break
			case 0x45:
				map.descriptionText = "$device.displayName remote operation disabled (UL requirement)"
				break
			case 0x46:
				map.descriptionText = "$device.displayName failed to perform operation due to device malfunction"
				break
			case 0x47:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName vacation mode enabled"
				} else {
					map.descriptionText = "$device.displayName vacation mode disabled"
				}
				break
			case 0x48:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName safety beam obstructed"
				} else {
					map.descriptionText = "$device.displayName safety beam obstruction cleared"
				}
				break
			case 0x49:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName door sensor ${cmd.eventParameter[0]} not detected"
				} else {
					map.descriptionText = "$device.displayName door sensor not detected"
				}
				break
			case 0x4A:
				if (cmd.eventParameter[0]) {
					map.descriptionText = "$device.displayName door sensor ${cmd.eventParameter[0]} has a low battery"
				} else {
					map.descriptionText = "$device.displayName door sensor has a low battery"
				}
				result << createEvent(name: "battery", value: 1, unit: "%", descriptionText: map.descriptionText)
				break
			case 0x4B:
				map.descriptionText = "$device.displayName detected a short in wall station wires"
				break
			case 0x4C:
				map.descriptionText = "$device.displayName is associated with non-Z-Wave remote control"
				break
			default:
				map.descriptionText = "$device.displayName: access control alarm $cmd.event"
				map.displayed = false
				break
		}
	} else if (cmd.notificationType == 7) {
		switch (cmd.event) {
			case 1:
			case 2:
				map.descriptionText = "$device.displayName detected intrusion"
				break
			case 3:
				map.descriptionText = "$device.displayName tampering detected: product cover removed"
				break
			case 4:
				map.descriptionText = "$device.displayName tampering detected: incorrect code"
				break
			case 7:
			case 8:
				map.descriptionText = "$device.displayName detected motion"
				break
			default:
				map.descriptionText = "$device.displayName: security alarm $cmd.event"
				map.displayed = false
		}
	} else if (cmd.notificationType){
		map.descriptionText = "$device.displayName: alarm type $cmd.notificationType event $cmd.event"
	} else {
		map.descriptionText = "$device.displayName: alarm $cmd.v1AlarmType is ${cmd.v1AlarmLevel == 255 ? 'active' : cmd.v1AlarmLevel ?: 'inactive'}"
	}
	result ? [createEvent(map), *result] : createEvent(map)
	if (state.debug) log.debug result
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd)
{
	[name: "switch", value: cmd.value ? "on" : "off", type: "physical", displayed: true, isStateChange: true]
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd) 
{
    [name: "switch", value: cmd.value ? "on" : "off", type: "physical", displayed: true, isStateChange: true]
}

def zwaveEvent(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinaryReport cmd)
{
	[name: "switch", value: cmd.value ? "on" : "off", type: "digital", displayed: true, isStateChange: true]
}

def zwaveEvent(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinarySet cmd)
{
    [name: "switch", value: cmd.value ? "on" : "off", type: "digital", displayed: true, isStateChange: true]
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	if (state.debug) log.debug "Unhandled: $cmd"
    createEvent(descriptionText: cmd.toString(), isStateChange: false)
}

def on() {
	def request = [
		zwave.basicV1.basicSet(value: 0xFF),
	    zwave.basicV1.basicGet(),
		zwave.switchBinaryV1.switchBinaryGet()
	]
    commands(request)
}

def off() {
	def request = [
		zwave.basicV1.basicSet(value: 0x00),
        zwave.basicV1.basicGet(),
		zwave.switchBinaryV1.switchBinaryGet()
	]
    commands(request)
}

def open() {
	def request = [
		zwave.barrierOperatorV1.barrierOperatorSet(requestedBarrierState: BarrierOperatorSet.REQUESTED_BARRIER_STATE_OPEN)
	]
    commands(request)
}

def close() {
	def request = [
		zwave.barrierOperatorV1.barrierOperatorSet(requestedBarrierState: BarrierOperatorSet.REQUESTED_BARRIER_STATE_CLOSE)
	]
    commands(request)
}

def refresh() {
	def request = [
		// get battery state
		zwave.configurationV1.configurationGet(parameterNumber: 42),
        zwave.basicV1.basicGet(),
		zwave.switchBinaryV1.switchBinaryGet(),
        zwave.barrierOperatorV1.barrierOperatorGet()
	]
    commands(request)
}

def configure() {
    if (state.debug) log.debug "settings: ${settings.inspect()}, state: ${state.inspect()}, zwaveHubNodeId: ${zwaveHubNodeId}}"
	def request = [
		//associate with group 1 and 2
        //zwave.associationV1.associationSet(groupingIdentifier:1, nodeId:zwaveHubNodeId),
        //zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId),
        zwave.associationV1.associationGet(groupingIdentifier:1),
        zwave.associationV1.associationGet(groupingIdentifier:2),

		// Get Version information
        zwave.versionV1.versionGet(),
        zwave.firmwareUpdateMdV2.firmwareMdGet(),
		
		// Enable to send notifications to associated devices (Group 1) when the state of Micro Switch’s load changed (0=nothing, 1=hail CC, 2=basic CC report)
        zwave.configurationV1.configurationSet(parameterNumber: 80, size: 1, configurationValue: [2]),
		zwave.configurationV1.configurationGet(parameterNumber: 80),
		
		//set startup tone
		zwave.configurationV1.configurationSet(parameterNumber: 32, size: 1, configurationValue: [1]),
		zwave.configurationV1.configurationGet(parameterNumber: 32),
        
		//get number of alarm tone
		zwave.configurationV1.configurationGet(parameterNumber: 36),
        
		// Set blink, sound, volume of opening action
		zwave.configurationV1.configurationSet(parameterNumber: 37, size: 4, configurationValue: [5,1,1,1]),
		zwave.configurationV1.configurationGet(parameterNumber: 37),
        
		// set blink, sound, volume of closing action
		zwave.configurationV1.configurationSet(parameterNumber: 38, size: 4, configurationValue: [10,2,1,1]),
		zwave.configurationV1.configurationGet(parameterNumber: 38),
        
		// set blink, sound, volume of unknown state
		zwave.configurationV1.configurationSet(parameterNumber: 39, size: 4, configurationValue: [1,3,1,0]),
		zwave.configurationV1.configurationGet(parameterNumber: 39),		

		// set blink, sound, volume of CLOSED state
		zwave.configurationV1.configurationSet(parameterNumber: 40, size: 4, configurationValue: [1,3,1,0]),
		zwave.configurationV1.configurationGet(parameterNumber: 40),

		// get battery state
		zwave.configurationV1.configurationGet(parameterNumber: 42),		

		// Can use the zwaveHubNodeId variable to add the hub to the device's associations:
		zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId)
    ]
	commands(request)
}

private setConfigured() {
	updateDataValue("configured", "true")
}

private isConfigured() {
	getDataValue("configured") == "true"
}

private command(physicalgraph.zwave.Command cmd) {
	if (state.sec) {
		zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
	} else {
		cmd.format()
	}
}

private commands(commands, delay=500) {
	delayBetween(commands.collect{ command(it) }, delay)
}
