/*
zooZ MiniPlug ZEN07 device handler for SmartThings version 1.0 by Robert Vandervoort 10/10/2016
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
for the specific language governing permissions and limitations under the License.

some code used from various SmartThings device type and metering code from ElasticDev

*/

metadata {
	definition (name: "zooZ MiniPlug ZEN07 - RV v1.0", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Energy Meter"
		capability "Power Meter"
		capability "Actuator"
		capability "Switch"
		capability "Configuration"
		capability "Polling"
		capability "Refresh"
		capability "Sensor"

		command "reset"
        command "getPower"
        command "getEnergy"
        command "getVolts"
        command "getCurrent"
        command "checkstate"
		
	// RAW 0 0 0x1001 0 0 0 d 0x5E 0x72 0x86 0x85 0x59 0x5A 0x73 0x70 0x25 0x27 0x71 0x32 0x20
	// MSR 027A-0003-0087
	
		fingerprint deviceId: "0x1001", inClusters: "0x5E,0x72,0x86,0x85,0x59,0x5A,0x73,0x70,0x25,0x27,0x71,0x32,0x20"
	}
	// simulator metadata
	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off": "command: 2003, payload: 00"

	for (int i = 0; i <= 10000; i += 1000) {
	    status "power  ${i} W": 
		    new physicalgraph.zwave.Zwave().meterV3.meterReport(scaledMeterValue: i, precision: 3, meterType: 4, scale: 2, size: 4).incomingMessage()
	}
	for (int i = 0; i <= 100; i += 10) {
	    status "energy  ${i} kWh":
		    new physicalgraph.zwave.Zwave().meterV3.meterReport(scaledMeterValue: i, precision: 3, meterType: 0, scale: 0, size: 4).incomingMessage()
	}
		// reply messages
		reply "2001FF,delay 100,2502": "command: 2503, payload: FF"
		reply "200100,delay 100,2502": "command: 2503, payload: 00"
	}

	// tile definitions
	tiles (scale: 2) {
    	multiAttributeTile(name:"main", type:"generic", width:6, height:4, canChangeIcon: true) {
			tileAttribute("device.switch", key: "PRIMARY_CONTROL") {
            	attributeState "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
				attributeState "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
            }
		}
        valueTile("power", "device.power", decoration: "flat", width: 2, height: 2) {
			state "default", label:'${currentValue} W', action:"getPower"
		}
		valueTile("energy", "device.energy", decoration: "flat", width: 2, height: 2) {
			state "default", label:'${currentValue} kWh', action:"getEnergy"
		}
		valueTile("current", "device.current", decoration: "flat", width: 2, height: 2) {
			state "default", label:'${currentValue} A', action:"getCurrent"
		}
        valueTile("voltage", "device.voltage", decoration: "flat", width: 2, height: 2) {
			state "default", label:'${currentValue} V', action:"getVoltage"
		}
		standardTile("reset", "device.energy", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'reset kWh', action:"reset"
		}
		standardTile("refresh", "device.power", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}
        standardTile("configure", "device.configure", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'config', action:"configure", icon:"st.secondary.refresh"
		}
        standardTile("check", "device.configure", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label:'check', action:"checkstate"
		}        
		main "main"
		details(["main","power","energy","current","voltage","reset","refresh","configure", "check"])
	}
	
    preferences { 
		input "energyReporting", "boolean", 
			title: "Energy usage reports",
			defaultValue: true,
			displayDuringSetup: true

		input "reportInterval", "integer", 
			title: "Energy report interval", 
			description: "The time interval in seconds for sending energy usage reports", 
			defaultValue: 300, 
            range: "1..4294967295‬",
			required: false, 
			displayDuringSetup: false

		input "reportThreshold", "integer",
			title: "Reporting threshold", 
			description: "The percentage of change required for sending energy usage reports", 
			defaultValue: 5, 
            range: "1..99",
			required: false, 
			displayDuringSetup: false
			
		input "loadWarning", "integer",
			title: "Load warning AMPs",
			description: "Set to a value in AMPs to provide heavy load warning notification.",
			defaultValue: 12,
			range: "1..12",
			required: false,
			displayDuringSetup: false
		
		input "overloadProtection", "integer",
			title: "Overload protection AMPs",
			description: "Must be higher than heavy load warning value. When this value is reached, the plug shuts down and must be reset.",
			defaultValue: 13,
			range: "1..15",
			required: false,
			displayDuringSetup: false

		input "manualOverride", "boolean", 
			title: "Switch manual control",
			description: "Enable or disable the switch on the plug. Good for tamper protection.",
			defaultValue: true,
			displayDuringSetup: false

		input "stateRecvovery", "boolean",
			title: "Return to last state after power failure?",
			defaultValue: true,
			displayDuringSetup: false
						
		input "ledNotifications", "boolean", 
			title: "LED Notifications?",
			description: "Enables or disables LED color change for heavy and overload notifications.",
			defaultValue: true,
			displayDuringSetup: false

		input "debugOutput", "boolean", 
			title: "Enable debug logging?",
			defaultValue: false,
			displayDuringSetup: true
	}
}

def updated() {
	updateDataValue("configured", "false")
	state.debug = ("true" == debugOutput)
    configure()
}

def parse(String description) {
    def result = null
    def cmd = zwave.parse(description)
    if (cmd) {
        result = zwaveEvent(cmd)
        if (state.debug) log.debug "Parsed ${cmd} to ${result.inspect()}"
    } else {
        log.debug "Non-parsed event: ${description}"
    }
    return result
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
	log.debug("ConfigurationReportv1 ${cmd.inspect()}")
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv2.ConfigurationReport cmd) {
	state.configParams << [(cmd.parameterNumber) : (cmd.configurationValue)]
    log.debug state.configParams
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv2.ConfigurationBulkReport cmd)
{
	log.debug "Got a bulk report, but don't know what to do with it."
}
 
def zwaveEvent(physicalgraph.zwave.commands.powerlevelv1.PowerlevelTestNodeReport cmd) {
	log.debug "===Power level test node report received=== ${device.displayName}: statusOfOperation: ${cmd.statusOfOperation} testFrameCount: ${cmd.testFrameCount} testNodeid: ${cmd.testNodeid}"
	def request = [
        physicalgraph.zwave.commands.powerlevelv1.PowerlevelGet()
    ]
    response(commands(request))
}

def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionReport cmd) {
	def fw = "${cmd.applicationVersion}.${cmd.applicationSubVersion}"
	updateDataValue("fw", fw)
	log.debug "---VERSION REPORT V1--- ${device.displayName} is running firmware version: $fw, Z-Wave version: ${cmd.zWaveProtocolVersion}.${cmd.zWaveProtocolSubVersion}"
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
	log.debug "manufacturerId:   ${cmd.manufacturerId}"
	log.debug "manufacturerName: ${cmd.manufacturerName}"
	log.debug "productId:        ${cmd.productId}"
	log.debug "productTypeId:    ${cmd.productTypeId}"
	def msr = String.format("%04X-%04X-%04X", cmd.manufacturerId, cmd.productTypeId, cmd.productId)
	updateDataValue("MSR", msr)
	createEvent([descriptionText: "$device.displayName MSR: $msr", isStateChange: false], displayed = true)
}

def zwaveEvent(physicalgraph.zwave.commands.meterv3.MeterReport cmd) {
    def meterTypes = ["Unknown", "Electric", "Gas", "Water"]
    def electricNames = ["energy", "energy", "power", "count",  "voltage", "current", "powerFactor",  "unknown"]
    def electricUnits = ["kWh",    "kVAh",   "W",     "pulses", "V",       "A",       "Power Factor", ""]

    //NOTE ScaledPreviousMeterValue does not always contain a value
    def previousValue = cmd.scaledPreviousMeterValue ?: 0

    def map = [ name: electricNames[cmd.scale], unit: electricUnits[cmd.scale], displayed: state.display]
    switch(cmd.scale) {
        case 0: //kWh
	    previousValue = device.currentValue("energy") ?: cmd.scaledPreviousMeterValue ?: 0
            map.value = cmd.scaledMeterValue
            break;
        case 1: //kVAh
            map.value = cmd.scaledMeterValue
            break;
        case 2: //Watts
            previousValue = device.currentValue("power") ?: cmd.scaledPreviousMeterValue ?: 0
            map.value = cmd.scaledMeterValue
            break;
        case 4: //Volts
            previousValue = device.currentValue("voltage") ?: cmd.scaledPreviousMeterValue ?: 0
            map.value = cmd.scaledMeterValue
            break;
        case 5: //Amps
            previousValue = device.currentValue("current") ?: cmd.scaledPreviousMeterValue ?: 0
            map.value = cmd.scaledMeterValue
            break;
    }
	map.isStateChange = true
	map.displayed = true
    createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
	def value = (cmd.value ? "on" : "off")
	def result = [createEvent(name: "switch", value: value, type: "physical", displayed: true, isStateChange: true)]
	return result
}

def zwaveEvent(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinaryReport cmd) {
	def value = (cmd.value ? "on" : "off")
	def result = [createEvent(name: "switch", value: value, type: "digital", displayed: true, isStateChange: true)]
	return result
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	log.debug "Unhandled: $cmd"
    createEvent(descriptionText: cmd.toString(), isStateChange: false)
}

def on() {
	def request = [
		zwave.basicV1.basicSet(value: 0xFF),
		zwave.switchBinaryV1.switchBinaryGet()
	]
    commands(request)
}

def off() {
	def request = [
		zwave.basicV1.basicSet(value: 0x00),
		zwave.switchBinaryV1.switchBinaryGet()
	]
    commands(request)
}

def checkstate() {
	// check our config state map
	log.debug "settings: ${settings.inspect()}, state: ${state.inspect()}"
}

def getPower() {
	zwave.meterV3.meterGet(scale: 2).format()	//Wattage
}

def getVoltage() {
	zwave.meterV3.meterGet(scale: 4).format()	//Volts
}

def getEnergy() {
	zwave.meterV3.meterGet(scale: 0).format()	//kWh
}

def getCurrent() {
	zwave.meterV3.meterGet(scale: 5).format()	//Amps
}

def poll() {
	def request = [
		zwave.switchBinaryV1.switchBinaryGet(),
        zwave.basicV1.basicGet(),
		zwave.meterV3.meterGet(scale: 0),	//kWh
        zwave.meterV3.meterGet(scale: 1),	//kVAh
		zwave.meterV3.meterGet(scale: 2),	//Wattage
		zwave.meterV3.meterGet(scale: 4),	//Volts
		zwave.meterV3.meterGet(scale: 5),	//Amps
        zwave.meterV3.meterGet(scale: 6)	//Power Factor
	]
    commands(request)
}

def refresh() {
	def request = [
        zwave.basicV1.basicGet(),
		zwave.switchBinaryV1.switchBinaryGet(),
        zwave.meterV3.meterGet(scale: 0),	//kWh
        zwave.meterV3.meterGet(scale: 1),	//kVAh
		zwave.meterV3.meterGet(scale: 2),	//Wattage
		zwave.meterV3.meterGet(scale: 4),	//Volts
		zwave.meterV3.meterGet(scale: 5),	//Amps
        zwave.meterV3.meterGet(scale: 6)	//Power Factor
	]
    commands(request)
}

def reset() {
	def request = [
		zwave.meterV3.meterReset(),
		zwave.meterV3.meterGet(scale: 0),	//kWh
        zwave.meterV3.meterGet(scale: 1),	//kVAh
		zwave.meterV3.meterGet(scale: 2),	//Wattage
		zwave.meterV3.meterGet(scale: 4),	//Volts
		zwave.meterV3.meterGet(scale: 5)	//Amps
	]
    commands(request)
}

def configure() {
    def request = [
    	// Get version and firmware info
		zwave.versionV1.versionGet(),
        zwave.firmwareUpdateMdV2.firmwareMdGet(),
        
        // get all the config values
		zwave.configurationV1.configurationGet(parameterNumber: 0x01),
		zwave.configurationV1.configurationGet(parameterNumber: 0x02),
		zwave.configurationV1.configurationGet(parameterNumber: 0x03),
		zwave.configurationV1.configurationGet(parameterNumber: 0x04),
		zwave.configurationV1.configurationGet(parameterNumber: 0x05),
		zwave.configurationV1.configurationGet(parameterNumber: 0x06),
		zwave.configurationV1.configurationGet(parameterNumber: 0x07),
		zwave.configurationV1.configurationGet(parameterNumber: 0x08),
		zwave.configurationV1.configurationGet(parameterNumber: 0x09),
		zwave.configurationV1.configurationGet(parameterNumber: 0x0A),
        
        //this doesn't seem to work in ST...
        //zwave.configurationV2.configurationBulkGet (parameterOffset: 0x0001, numberOfParameters: 10),
        
        // enable or disable energy metering reports 0=disabled 1=enabled
		zwave.configurationV1.configurationSet(parameterNumber: 0x01, size: 1, scaledConfigurationValue: 1),

		// Interval in seconds for sending energy metering reports
		zwave.configurationV1.configurationSet(parameterNumber: 0x02, size: 2, scaledConfigurationValue: 300),

		// Overload protection - set the number of AMPS you want to limit the plug to. 13 is what it is designed for.
		// when this value is hit, overload notification messages are sent and you have to press the button on the plug to reset it.
		zwave.configurationV1.configurationSet(parameterNumber: 0x03, size: 1, scaledConfigurationValue: 13),
		
		// Heavy load warning AMP threshold - the plug led will turn yellow when you reach this AMP value. must be less than parameter 3/
		zwave.configurationV1.configurationSet(parameterNumber: 0x04, size: 1, scaledConfigurationValue: 12),

		//  LED Notifications - 0=disabled 1=enabled
		zwave.configurationV1.configurationSet(parameterNumber: 0x05, size: 1, scaledConfigurationValue: 1),
				
		// percentage of change in any energy metric for a report to be sent - threshold reporting
		zwave.configurationV1.configurationSet(parameterNumber: 0x06, size: 1, scaledConfigurationValue: 5),

		// power failure recovery state - 0=remain off when power restored 1=return to last state when power restored
		zwave.configurationV1.configurationSet(parameterNumber: 0x07, size: 1, scaledConfigurationValue: 1),
		
		// auto power off timer - 0=disabled 1=enabled
		zwave.configurationV1.configurationSet(parameterNumber: 0x08, size: 1, scaledConfigurationValue: 12),

		// auto power off timer duration - a value in minutes 1-65535
		zwave.configurationV1.configurationSet(parameterNumber: 0x09, size: 2, scaledConfigurationValue: 150),

		// manual control power on/off control - 0=disable action button 1=enable action button
		zwave.configurationV1.configurationSet(parameterNumber: 0x0A, size: 1, scaledConfigurationValue: 1),
		
		// Can use the zwaveHubNodeId variable to add the hub to the device's associations:
		zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId)
    ]
	commands(request) + setConfigured()
}

private setConfigured() {
	updateDataValue("configured", "true")
}

private isConfigured() {
	getDataValue("configured") == "true"
}

private command(physicalgraph.zwave.Command cmd) {
	cmd.format()
}

private commands(commands, delay=1250) {
	delayBetween(commands.collect{ command(it) }, delay)
}