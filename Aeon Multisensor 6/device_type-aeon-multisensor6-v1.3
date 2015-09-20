/**
 * V 1.3 of Multisensor 6 code 9/14/2015
 *  Original code for gen5 Copyright 2015 SmartThings, modified for use on Multisensor 6 by Robert Vandervoort 6/19/2015
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
	definition (name: "rv Aeon Multisensor 6", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Motion Sensor"
		capability "Touch Sensor"
		capability "Temperature Measurement"
		capability "Relative Humidity Measurement"
		capability "Illuminance Measurement"
		capability "Ultraviolet Index" 
		capability "Configuration"
		capability "Sensor"
		capability "Battery"
		
		attribute "tamper", "enum", ["detected", "clear"]
		// CC supported 94, 134, 114, 132, 89, 133, 115, 113, 128, 48, 49, 112, 152, 122
		fingerprint deviceId: "0x2101", inClusters: "0x5E,0x86,0x72,0x59,0x85,0x73,0x71,0x84,0x80,0x30,0x31,0x70,0xEF,0x5A,0x98,0x7A"
		}
	simulator {
		status "no motion" : "command: 9881, payload: 00300300"
		status "motion"    : "command: 9881, payload: 003003FF"
        status "clear" : " command: 9881, payload: 0071050000000007030000"
        status "tamper" : "command: 9881, payload: 007105000000FF07030000"
        
        for (int i = 0; i <= 100; i += 20) {
			status "temperature ${i}F": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().sensorMultilevelV2.sensorMultilevelReport(
                	scaledSensorValue: i,
                    precision: 1,
                    sensorType: 1,
                    scale: 1
				)
			).incomingMessage()
		}
		for (int i = 0; i <= 100; i += 20) {
			status "RH ${i}%": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().sensorMultilevelV2.sensorMultilevelReport(
                	scaledSensorValue: i,
                    sensorType: 5
            	)
			).incomingMessage()
		}
		for (int i in [0, 20, 89, 100, 200, 500, 1000]) {
			status "illuminance ${i} lux": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().sensorMultilevelV2.sensorMultilevelReport(
                scaledSensorValue: i,
                sensorType: 3
                )
			).incomingMessage()
		}
		for (int i = 0; i <= 11; i += 1) {
			status "ultravioletultravioletIndex ${i}": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().sensorMultilevelV2.sensorMultilevelReport(
                scaledSensorValue: i,
                sensorType: 27
                )
			).incomingMessage()
		}
		for (int i in [0, 5, 10, 15, 50, 99, 100]) {
			status "battery ${i}%": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().batteryV1.batteryReport(
                batteryLevel: i
                )
			).incomingMessage()
		}
		status "low battery alert": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().batteryV1.batteryReport(
            	batteryLevel: 255
            	)
			).incomingMessage()
		status "wake up": "command: 8407, payload:"
	}
	tiles {
		standardTile("motion","device.motion") {
            	state "active",label:'motion',icon:"st.motion.motion.active",backgroundColor:"#53a7c0"
                state "inactive",label:'no motion',icon:"st.motion.motion.inactive",backgroundColor:"#ffffff"
			}
		valueTile("temperature","device.temperature",inactiveLabel: false) {
            	state "temperature",label:'${currentValue}°',backgroundColors:[
                	[value: 32, color: "#153591"],
                    [value: 44, color: "#1e9cbb"],
                    [value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 92, color: "#d04e00"],
					[value: 98, color: "#bc2323"]
				]
			}
		valueTile(
        	"humidity","device.humidity",inactiveLabel: false) {
            	state "humidity",label:'RH ${currentValue} %',unit:""
			}
		valueTile(
        	"illuminance","device.illuminance",inactiveLabel: false) {
            	state "luminosity",label:'${currentValue} ${unit}', unit:"lux", backgroundColors:[
                	[value: 0, color: "#000000"],
                    [value: 47, color: "#1A1A0A"],
                    [value: 94, color: "#333314"],
					[value: 141, color: "#4C4C1F"],
					[value: 188, color: "#666629"],
					[value: 235, color: "#808033"],
					[value: 282, color: "#99993D"],
					[value: 329, color: "#B2B247"],
					[value: 376, color: "#CCCC52"],
					[value: 423, color: "#E6E65C"],
					[value: 470, color: "#FFFF66"],
					[value: 517, color: "#FFFF75"],
                    [value: 564, color: "#FFFF85"],
                    [value: 611, color: "#FFFF94"],
                    [value: 658, color: "#FFFFA3"],
                    [value: 705, color: "#FFFFB2"],
                    [value: 752, color: "#FFFFC2"],
                    [value: 799, color: "#FFFFD1"],
                    [value: 846, color: "#FFFFE0"],
                    [value: 900, color: "#FFFFF0"],
                    [value: 1000, color: "#FFFFFF"]
				]
			}
		valueTile(
        	"ultravioletIndex","device.ultravioletIndex",inactiveLabel: false) {
				state "ultravioletIndex",label:'${currentValue} UV INDEX',unit:""
			}
		standardTile(
        	"tamper","device.tamper") {
				state "tamper",label:'tamper',icon:"st.motion.motion.active",backgroundColor:"#ff0000"
                state "clear",label:'clear',icon:"st.motion.motion.inactive",backgroundColor:"#00ff00"
			}
		valueTile(
			"battery", "device.battery", inactiveLabel: false, decoration: "flat") {
			state "battery", label:'${currentValue}% battery', unit:""
		}
		standardTile(
        	"configure","device.configure",inactiveLabel: false, decoration: "flat") {
				state "configure", label:'insecure config', action:"configure", icon:"st.secondary.tools"
			}
        standardTile(
        	"configureAfterSecure", "device.configure", inactiveLabel: false, decoration: "flat") {
				state "configure", label:'secure config', action:"configureAfterSecure", icon:"st.secondary.tools"
			}
		main([
        	"motion","tamper","temperature","humidity","illuminance","ultravioletIndex"
            ])
		details([
        	"motion","tamper","temperature","humidity","illuminance","ultravioletIndex","battery","configure","configureAfterSecure"
            ])
	}
    preferences {
		input "tempoffset",
			"number",
			title: "Temperature offset",
            description: "negative values reduce the monitored value positive ones add to it",
            range: "..",
			defaultValue: 0,
            required: false,
            displayDuringSetup: false
		input "humidityoffset",
        	"number",
            title: "Humidity offset",
            description: "negative values reduce the monitored value positive ones add to it",
			range: "..",
			defaultValue: 0,
			required: false,
            displayDuringSetup: false
		input "luminanceoffset",
          	"number",
            title: "Luminance offset",
            description: "negative values reduce the monitored value positive ones add to it",
            range: "..",
			defaultValue: 0,
            required: false,
	        displayDuringSetup: false
		input "ultravioletoffset",
          	"number",
            title: "Ultraviolet offset",
            description: "negative values reduce the monitored value positive ones add to it",
            range: "..",
			defaultValue: 0,
	        required: false,
			displayDuringSetup: false
		input "PIRsensitivity",
	        "number",
    	    title: "PIR motion sensitivity",
			description: "A value from 0-5, from disabled to high sensitivity",
			defaultValue: 1,
			required: true,
			displayDuringSetup: true
		input "ReportingInterval",
        	"number",
            title: "Report data interval",
            description: "A value in seconds.",
            defaultValue: 300,
            required: true,
            displayDuringSetup: true
	}
}

def parse(String description) {
	def result = null
    if (description == "updated") {
        result = null
		}
	else {
       	def cmd = zwave.parse(description, [0x31: 5, 0x30: 2, 0x84: 1])
		if (cmd) {
       		result = zwaveEvent(cmd)
		}
	}
	log.debug "Parsed '${description}' to ${result.inspect()}"
	return result
}
def zwaveEvent(physicalgraph.zwave.commands.wakeupv1.WakeUpNotification cmd)
{
	def result = [createEvent(descriptionText: "${device.displayName} woke up", isStateChange: false)]

	if (!isConfigured()) {
		// we're still in the process of configuring a newly joined device
		log.debug("have not sent config yet - not sending wakeUpNoMoreInformation yet")
        result += response(configureAfterSecure())
        result += response(configure())
	} else {
		result += response(zwave.wakeUpV1.wakeUpNoMoreInformation())
	}
	result
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x31: 5, 0x30: 2, 0x84: 1])
	log.debug "encapsulated: ${encapsulatedCommand}"
	if (encapsulatedCommand) {
		zwaveEvent(encapsulatedCommand)
	} else {
		log.warn "Unable to extract encapsulated cmd from $cmd"
		createEvent(descriptionText: cmd.toString())
	}
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityCommandsSupportedReport cmd) {
    log.debug "Received SecurityCommandsSupportedReport"
	// response(configureAfterSecure())
}

def zwaveEvent(physicalgraph.zwave.commands.batteryv1.BatteryReport cmd) {
	def map = [ name: "battery", unit: "%" ]
	if (cmd.batteryLevel == 0xFF) {
		map.value = 1
		map.descriptionText = "${device.displayName} battery is low"
		map.isStateChange = true
	} else {
		map.value = cmd.batteryLevel
	}
	state.lastbatt = new Date().time
	createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv5.SensorMultilevelReport cmd)
{
	def map = [:]
	switch (cmd.sensorType) {
		case 1:
			map.name = "temperature"
			def cmdScale = cmd.scale == 1 ? "F" : "C"
			map.value = convertTemperatureIfNeeded(cmd.scaledSensorValue, cmdScale, cmd.precision)
			map.unit = getTemperatureScale()
			break;
		case 3:
			map.name = "illuminance"
			map.value = cmd.scaledSensorValue.toInteger()
			map.unit = "lux"
			break;
        case 5:
			map.name = "humidity"
			map.value = cmd.scaledSensorValue.toInteger()
			map.unit = "%"
			break;
		case 27:
        	map.name = "ultravioletIndex"
            map.value = cmd.scaledSensorValue.toInteger()
            map.unit = ""
            break;
		default:
			map.descriptionText = cmd.toString()
	}
	createEvent(map)
}

def motionEvent(value) {
	def map = [name: "motion"]
	if (value) {
		map.value = "active"
		map.descriptionText = "$device.displayName detected motion"
	} else {
		map.value = "inactive"
		map.descriptionText = "$device.displayName motion has stopped"
	}
	createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.sensorbinaryv2.SensorBinaryReport cmd) {
	// setConfigured()
	motionEvent(cmd.sensorValue)
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd) {
	motionEvent(cmd.value)
}

def zwaveEvent(physicalgraph.zwave.commands.notificationv3.NotificationReport cmd) {
	def result = []
	if (cmd.notificationType == 7) {
		switch (cmd.event) {
			case 0:
				result << motionEvent(0)
				result << createEvent(name: "tamper", value: "clear", descriptionText: "$device.displayName tamper cleared")
				break
			case 3:
				result << createEvent(name: "tamper", value: "detected", descriptionText: "$device.displayName was moved")
				break
			case 7:
				result << motionEvent(1)
				break
		}
	} else {
		result << createEvent(descriptionText: cmd.toString(), isStateChange: false)
	}
	result
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	createEvent(descriptionText: cmd.toString(), isStateChange: false)
}

def configureAfterSecure() {
	log.debug "configureAfterSecure"
    log.debug "PIRsensitivity: $PIRsensitivity, Reporting Interval: $ReportingInterval, Temp offset: $tempoffset, Humidity offset: $humidityoffset, Luminance offset: $luminanceoffset, UV offset: $ultravioletoffset"

	def PIRsens = 1
	if (PIRsensitivity) {
		PIRsens=PIRsensitivity.toInteger()
	}
	def ReportingInt = 300
	if (ReportingInterval) {
		ReportingInt=ReportingInterval.toInteger()
	}
	def tempoff = 0
	if (tempoffset) {
		if(tempoffset < 0) {
			tempoff=(255-tempoffset)
		}
		if(tempoffset > 0) {
			tempoff=(0+tempoffset)
		}
	}
	def humidityoff = 0
	if (humidityoffset) {
		humidityoff=humidityoffset.toInteger()
	}
	def luminanceoff = 0
	if (luminanceoffset) {
		luminanceoff=luminanceoffset.toInteger()
	}
	def ultravioletoff = 0
	if (ultravioletoffset) {
		ultravioletoff=ultravioletoffset.toInteger()
	}
    //log.debug "PIRsens: $PIRsens, ReportingInt: $ReportingInt, Tempoffset: $tempoff, Humidityoff: $humidityoff, Luminanceoff: $luminanceoff, UVoff: $ultravioletoff"
	def request = [
		// set wakeup interval to 5 mins
		zwave.wakeUpV1.wakeUpIntervalSet(seconds:300, nodeid:zwaveHubNodeId),
		// send temperature, humidity, illuminance, ultraviolet based on reporting interval preference default 5 mins
		zwave.configurationV1.configurationSet(parameterNumber: 0x65, size: 4, scaledConfigurationValue: 128|64|32|16),
		// configure frequency of reporting 
		zwave.configurationV1.configurationSet(parameterNumber: 0x6F,size: 4, scaledConfigurationValue: ReportingInt),
		// send battery every 20 hours
		zwave.configurationV1.configurationSet(parameterNumber: 0x66, size: 4, scaledConfigurationValue: 1),
		zwave.configurationV1.configurationSet(parameterNumber: 0x70, size: 4, scaledConfigurationValue: 20*60*60),
        // send no-motion report 20 seconds after motion stops
		zwave.configurationV1.configurationSet(parameterNumber: 0x03, size: 2, scaledConfigurationValue: 20),
		// enable motion sensor and set sensitivity
        zwave.configurationV1.configurationSet(parameterNumber: 0x04, size: 1, scaledConfigurationValue: PIRsens),
		// send binary sensor report for motion
		zwave.configurationV1.configurationSet(parameterNumber: 0x05, size: 1, scaledConfigurationValue: 2),
		// Enable the function of touch sensor
        // zwave.configurationV1.configurationSet(parameterNumber: 0x07, size: 1, scaledConfigurationValue: 1),
		// disable notification-style motion events
		zwave.notificationV3.notificationSet(notificationType: 7, notificationStatus: 0),
        // configure temp offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xC9, size: 2, scaledConfigurationValue: tempoff),
        // configure humidity offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xCA, size: 2, scaledConfigurationValue: humidityoff),
        // configure luminance offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xCB, size: 2, scaledConfigurationValue: luminanceoff),
        // configure ultraviolet offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xCC, size: 2, scaledConfigurationValue: ultravioletoff), 

		zwave.batteryV1.batteryGet(),
		zwave.sensorBinaryV2.sensorBinaryGet(),
        
		// Can use the zwaveHubNodeId variable to add the hub to the device's associations:
		zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId)
    ]
	
    //log.debug request
    secureSequence(request) + ["delay 20000", zwave.wakeUpV1.wakeUpNoMoreInformation().format()]
	setConfigured()
}

def configure() {
    //["delay 20000"] + secure(zwave.securityV1.securityCommandsSupportedGet())
    log.debug "configure"
	log.debug "PIRsensitivity: $PIRsensitivity, Reporting Interval: $ReportingInterval, Temp offset: $tempoffset, Humidity offset: $humidityoffset, Luminance offset: $luminanceoffset, UV offset: $ultravioletoffset"

	def PIRsens = 1
	if (PIRsensitivity) {
		PIRsens=PIRsensitivity.toInteger()
	}
	def ReportingInt = 300
	if (ReportingInterval) {
		ReportingInt=ReportingInterval.toInteger()
	}
	def tempoff = 0
	if (tempoffset) {
		if(tempoffset < 0) {
			tempoff=(255-tempoffset)
		}
		if(tempoffset > 0) {
			tempoff=(0+tempoffset)
		}
	}
	def humidityoff = 0
	if (humidityoffset) {
		humidityoff=humidityoffset.toInteger()
	}
	def luminanceoff = 0
	if (luminanceoffset) {
		luminanceoff=luminanceoffset.toInteger()
	}
	def ultravioletoff = 0
	if (ultravioletoffset) {
		ultravioletoff=ultravioletoffset.toInteger()
	}
    log.debug "PIRsens: $PIRsens, ReportingInt: $ReportingInt, Tempoffset: $tempoff, Humidityoff: $humidityoff, Luminanceoff: $luminanceoff, UVoff: $ultravioletoff"

	delayBetween([
		// set wakeup interval to 5 minutes
		zwave.wakeUpV1.wakeUpIntervalSet(seconds:300, nodeid:zwaveHubNodeId).format(),
		// send temperature, humidity, illuminance, ultraviolet based on reporting interval preference default 5 mins
		zwave.configurationV1.configurationSet(parameterNumber: 0x65, size: 4, scaledConfigurationValue: 128|64|32|16).format(),
		zwave.configurationV1.configurationSet(parameterNumber: 0x6F, size: 4, scaledConfigurationValue: ReportingInt).format(),

		// send battery every 20 hours
		zwave.configurationV1.configurationSet(parameterNumber: 0x66, size: 4, scaledConfigurationValue: 1).format(),
		zwave.configurationV1.configurationSet(parameterNumber: 0x70, size: 4, scaledConfigurationValue: 20*60*60).format(),

        // send no-motion report 20 seconds after motion stops
		zwave.configurationV1.configurationSet(parameterNumber: 0x03, size: 2, scaledConfigurationValue: 20).format(),

		// enable motion sensor and set min sensitivity
        zwave.configurationV1.configurationSet(parameterNumber: 0x04, size: 1, scaledConfigurationValue: PIRsens).format(),

		// send binary sensor report for motion
		zwave.configurationV1.configurationSet(parameterNumber: 0x05, size: 1, scaledConfigurationValue: 2).format(),

		// Enable the function of touch sensor
        //zwave.configurationV1.configurationSet(parameterNumber: 0x07, size: 1, scaledConfigurationValue: 1).format(),

		// disable notification-style motion events
		zwave.notificationV3.notificationSet(notificationType: 7, notificationStatus: 0).format(),
        
        // configure temp offset
        zwave.configurationV1.configurationSet(parameterNumber: 0xC9, size: 2, scaledConfigurationValue: tempoff).format(),
        
        // configure humidity offset
        zwave.configurationV1.configurationSet(parameterNumber: 0xCA, size: 2, scaledConfigurationValue: humidityoff).format(),
        
        // configure luminance offset
        zwave.configurationV1.configurationSet(parameterNumber: 0xCB, size: 2, scaledConfigurationValue: luminanceoff).format(),
        
        // configure ultraviolet offset
        zwave.configurationV1.configurationSet(parameterNumber: 0xCC, size: 2, scaledConfigurationValue: ultravioletoff).format(),
        
		zwave.batteryV1.batteryGet(),
		zwave.sensorBinaryV2.sensorBinaryGet(),
		
        // Can use the zwaveHubNodeId variable to add the hub to the device's associations:
		zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId)
    ])	
	zwave.wakeUpV1.wakeUpNoMoreInformation().format()
	setConfigured()
}

def setConfigured() {
	device.updateDataValue("configured", "true")
}

def isConfigured() {
	Boolean configured = device.getDataValue(["configured"]) as Boolean	
	return configured
}

private secure(physicalgraph.zwave.Command cmd) {
	zwave.securityV1.securityMessageEncapsulation().encapsulate(cmd).format()
}

private secureSequence(commands, delay=200) {
	delayBetween(commands.collect{ secure(it) }, delay)
}
def updated() {
	log.debug "updated()"
    log.debug "PIRsensitivity: $PIRsensitivity, Reporting Interval: $ReportingInterval, Temp offset: $tempoffset, Humidity offset: $humidityoffset, Luminance offset: $luminanceoffset, UV offset: $ultravioletoffset"
    //configure()
}