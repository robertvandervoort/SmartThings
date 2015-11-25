/*
 * V 2.0 of Multisensor 6 code 9/14/2015
 * Uses some original code from @Duncan Aeon Multisensor 6 code for secure configuration, Copyright 2015 SmartThings, modified for setting
 * preferences around configuration and the reporting of tampering and ultraviolet index, and reconfiguration after pairing.
 * 2.0 brings about the new style look with multiattribute tile for temp and humidity
 * Robert Vandervoort 6/19/2015 - 11/5/2015
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
 * INSTRUCTIONS: Pair device to SmartThings by double tapping the inclusion button. Allow it to join with SmartThings device type. After
 * adding, go to device entry in the website, edit it, change type to Aeon Multisensor 6 - RV, open device in SmartThings app, edit
 * edit preferences to your liking but do not save yet, short press action button on sensor one time and the light should go blue, hit save
 * in the app, a couple seconds later the light should go out (you can verify in live logging that it says it woke up and settings were sent
 * over).. Now on device screen, short press the action button one more time and hit secure configure button on the app screen... Your sensor
 * should now follow your polling interval, but will go no lower than 5 minute intervals unless a large enough change happens to warrant it.
 * motion activates immediately and clears after 20 seconds. tamper activates immediately and also clears after 20 seconds. Currently tamper
 * is just used for logging and as an indicator on the screen. If requests for functionality outside of that are required please message me.
 */

 metadata {
	definition (name: "Aeon Multisensor 6 - RV 2.0", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Motion Sensor"
		capability "Temperature Measurement"
		capability "Relative Humidity Measurement"
		capability "Illuminance Measurement"
		capability "Ultraviolet Index" 
		capability "Configuration"
		capability "Sensor"
		capability "Battery"
		
		// CCs supported - 94, 134, 114, 132, 89, 133, 115, 113, 128, 48, 49, 112, 152, 122
		attribute "tamper", "enum", ["detected", "clear"]
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
		for (int i in [0, 1, 2, 8, 12, 16, 20, 24, 30, 64, 82, 100, 200, 500, 1000]) {
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
	tiles (scale: 2) {
		multiAttributeTile(name:"main", type:"generic", width:6, height:4) {
			tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
            	attributeState "temperature",label:'${currentValue}°',backgroundColors:[
                	[value: 32, color: "#153591"],
                    [value: 44, color: "#1e9cbb"],
                    [value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 92, color: "#d04e00"],
					[value: 98, color: "#bc2323"]
				]
            }
            tileAttribute("device.humidity", key: "SECONDARY_CONTROL") {
                attributeState "humidity",label:'RH ${currentValue} %',unit:""
            }
		}
        standardTile("motion","device.motion", width: 2, height: 2) {
            	state "active",label:'motion',icon:"st.motion.motion.active",backgroundColor:"#53a7c0"
                state "inactive",label:'no motion',icon:"st.motion.motion.inactive",backgroundColor:"#ffffff"
			}
		valueTile("temperature","device.temperature", width: 2, height: 2) {
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
        	"illuminance","device.illuminance", width: 2, height: 2) {
            	state "luminosity",label:'${currentValue} ${unit}', unit:"lux", backgroundColors:[
                	[value: 0, color: "#000000"],
                    [value: 1, color: "#060053"],
                    [value: 3, color: "#3E3900"],
                    [value: 12, color: "#8E8400"],
					[value: 24, color: "#C5C08B"],
					[value: 36, color: "#DAD7B6"],
					[value: 128, color: "#F3F2E9"],
                    [value: 1000, color: "#FFFFFF"]
				]
			}
		valueTile(
        	"ultravioletIndex","device.ultravioletIndex", width: 2, height: 2) {
				state "ultravioletIndex",label:'${currentValue} UV INDEX',unit:""
			}
		standardTile(
        	"tamper","device.tamper", width: 2, height: 2) {
				state "tamper",label:'tamper',icon:"st.motion.motion.active",backgroundColor:"#ff0000"
                state "clear",label:'clear',icon:"st.motion.motion.inactive",backgroundColor:"#00ff00"
			}
		valueTile(
			"battery", "device.battery", decoration: "flat", width: 2, height: 2) {
			state "battery", label:'${currentValue}% battery', unit:""
		}
		standardTile(
        	"configure","device.configure", decoration: "flat", width: 2, height: 2) {
				state "configure", label:'config', action:"configure", icon:"st.secondary.tools"
			}
		main([
        	"main"
            ])
		details([
        	"main","illuminance","ultravioletIndex","motion","tamper","battery","configure"
            ])
	}
	
    preferences {
		input "tempoffset",
			"number",
			title: "Temperature offset",
            description: "negative values reduce the monitored value positive ones add to it",
            range: "-11..11",
			defaultValue: 0,
            required: false,
            displayDuringSetup: false
		input "humidityoffset",
        	"number",
            title: "Humidity offset",
            description: "negative values reduce the monitored value positive ones add to it",
			range: "-50..50",
			defaultValue: 0,
			required: false,
            displayDuringSetup: false
		input "luminanceoffset",
          	"number",
            title: "Luminance offset",
            description: "negative values reduce the monitored value positive ones add to it",
            range: "-1000..1000",
			defaultValue: 0,
            required: false,
	        displayDuringSetup: false
		input "ultravioletoffset",
          	"number",
            title: "Ultraviolet offset",
            description: "negative values reduce the monitored value positive ones add to it",
            range: "-10..10",
			defaultValue: 0,
	        required: false,
			displayDuringSetup: false
		input "PIRsensitivity",
	        "number",
    	    title: "PIR motion sensitivity",
			description: "A value from 0-5, from disabled to high sensitivity",
			range: "0..5",
			defaultValue: 1,
			required: true,
			displayDuringSetup: true
		input "MotionReset",
	        "number",
    	    title: "PIR reset time",
			description: "Number of seconds to wait to report motion cleared after a motion event if there is no motion detected.",
			defaultValue: 20,
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

def updated()
{
	if (state.sec && !isConfigured()) {
		// in case we miss the SCSR
		response(configure())
	}
}

def parse(String description)
{
	def result = null
	if (description.startsWith("Err 106")) {
		state.sec = 0
		result = createEvent( name: "secureInclusion", value: "failed", isStateChange: true,
			descriptionText: "This sensor failed to complete the network security key exchange. If you are unable to control it via SmartThings, you must remove it from your network and add it again.")
	} else if (description != "updated") {
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
		log.debug("late configure")
		result += response(configure())
	} else {
		result += response(zwave.wakeUpV1.wakeUpNoMoreInformation())
	}
	result
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x31: 5, 0x30: 2, 0x84: 1])
	state.sec = 1
	log.debug "encapsulated: ${encapsulatedCommand}"
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

def zwaveEvent(physicalgraph.zwave.commands.configurationv2.ConfigurationReport cmd) {
    log.debug "---CONFIGURATION REPORT V2--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
    log.debug "---CONFIGURATION REPORT V1--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
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
	state.lastbatt = now()
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
	setConfigured()
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

def configure() {
	// This sensor joins as a secure device if you double-click the button to include it
	//if (device.device.rawDescription =~ /98/ && !state.sec) {
	//	log.debug "Multi 6 not sending configure until secure"
	//	return []
	//}
	log.debug "--Sending configuration command to Multisensor 6--"
    log.debug "Prefernces settings: PIRsensitivity: $PIRsensitivity, Reporting Interval: $ReportingInterval, Temp offset: $tempoffset, Humidity offset: $humidityoffset, Luminance offset: $luminanceoffset, UV offset: $ultravioletoffset"
	
	def PIRsens = 1
	if (PIRsensitivity) {
		PIRsens=PIRsensitivity.toInteger()
	}
    def MotionRst = 20
	if (MotionReset) {
		MotionRst=MotionReset.toInteger()
	}
	def ReportingInt = 300
	if (ReportingInterval) {
		ReportingInt=ReportingInterval.toInteger()
	}
	def tempoff = 0
	if (tempoffset) {
		tempoff=tempoffset*10
	}
	def humidityoff = 0
	if (humidityoffset) {
		humidityoff=humidityoffset
	}
	def luminanceoff = 0
	if (luminanceoffset) {
		luminanceoff=luminanceoffset
	}
	def ultravioletoff = 0
	if (ultravioletoffset) {
		ultravioletoff=ultravioletoffset
	}
    log.debug "Setting - PIRsens: $PIRsens, ReportingInt: $ReportingInt, Tempoffset: $tempoff, Humidityoff: $humidityoff, Luminanceoff: $luminanceoff, UVoff: $ultravioletoff"
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
		zwave.configurationV1.configurationSet(parameterNumber: 0x03, size: 2, scaledConfigurationValue: MotionRst),
		
		// enable motion sensor and set sensitivity
        zwave.configurationV1.configurationSet(parameterNumber: 0x04, size: 1, scaledConfigurationValue: PIRsens),
		
		// send binary sensor report for motion
		zwave.configurationV1.configurationSet(parameterNumber: 0x05, size: 1, scaledConfigurationValue: 2),
		
		// Enable the function of touch sensor
        zwave.configurationV1.configurationSet(parameterNumber: 0x07, size: 1, scaledConfigurationValue: 1),
		
		// disable notification-style motion events
		// zwave.notificationV3.notificationSet(notificationType: 7, notificationStatus: 0),
		
        // configure temp offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xC9, size: 1, scaledConfigurationValue: tempoff),
		
        // configure humidity offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xCA, size: 1, scaledConfigurationValue: humidityoff),
		
        // configure luminance offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xCB, size: 2, scaledConfigurationValue: luminanceoff),
		
        // configure ultraviolet offset
		zwave.configurationV1.configurationSet(parameterNumber: 0xCC, size: 1, scaledConfigurationValue: ultravioletoff), 
		
        zwave.configurationV1.configurationGet(parameterNumber: 0x6F),
    	zwave.configurationV1.configurationGet(parameterNumber: 0x03),
    	zwave.configurationV1.configurationGet(parameterNumber: 0x04),
    	zwave.configurationV1.configurationGet(parameterNumber: 0x28),
    	zwave.configurationV1.configurationGet(parameterNumber: 0xC9),
        zwave.configurationV1.configurationGet(parameterNumber: 0xCA),
        zwave.configurationV1.configurationGet(parameterNumber: 0xCB),
        zwave.configurationV1.configurationGet(parameterNumber: 0xCC),

		zwave.batteryV1.batteryGet(),
		zwave.sensorBinaryV2.sensorBinaryGet(),
        
		// Can use the zwaveHubNodeId variable to add the hub to the device's associations:
		zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId)
    ]
	commands(request) + ["delay 20000", zwave.wakeUpV1.wakeUpNoMoreInformation().format()]
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

private commands(commands, delay=200) {
	delayBetween(commands.collect{ command(it) }, delay)
}
