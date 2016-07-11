/*
 * V 1.2 of zooZ 4-in-1 sensor code 6/12/2016
 * by Robert Vandervoort
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
---------------------------------------------------------------------------------------------------------------------------
   INSTRUCTIONS:
   * First you will need to create a new device handler in SmartThings. Log in to the IDE and click on Device Handlers in the top menu.
   Then click "from code" below. You'l be presented with a blank area where you can copy this code and paste it in.
   * Next, scroll to the bottom and click create. Now click save, then click publish > for me.
   * You should now see this device type in your list of device handlers. Now we need to pair the device.
   * Put SmartThings into inclusion mode by going through the add new device dialog.
   On the bottom of the zooZ 4-in-1 you will see several small holes. There is a series of four holes and a hole all by itself.
   You'll need a paperclip or something similar that fits into the hole. While holding the sensor a foot or so from the hub,
   and with SmartThings in inclusion mode, gently depress the button inside the single hole twice rapidly. The light should blink.
   Please wait a few moments (usually 10 seconds or so) and you should see "zooZ 4-in-1 sensor - RV 1.0" appear in the found devices area.
   * Touch the "zooZ 4-in-1 sensor - RV 1.0" to configure. Here we will set your temperature scale preferecne and motion preferences.
   After you make your choices, touch done in the app. 
   * Now go to your devices list in the SmartThings app and open up the newly added sensor.
   * In order to get the configuration sent and initial sensor data, we need to wake the sensor up. Press the button on the device
   one time while holding it close to the hub. If you pressed the button the sensor should blink yellow once. Wait about 30 seconds,
   then refresh (drag the screen down) and you should see data on the screen for temp, humidity, illuminance, and battery.
   ** If you do not, please wait a minute or so and refresh the screen again by sliding it down.
   * If you still do not see anything after refreshing, touch the gear icon to enter the device preferences screen (make any adjustments or not) and then touch done.
   This will reset the configuration state so the device can be configured again and polled for data. Once you're back at the device screen,
   while holding the sensor close to the hub, press the small button we pressed earlier one time, making sure that the sensor blinks
   (if it doesn't, press the button again) and wait a minute or so, then refresh the device screen in the app again.
   You should see data on the screen now. 
   *** IF YOU STILL ARE NOT GETTING DATA, go back to the preferences screen and enable debug logging, and touch done. You do not need
   to press the button on the sensor again at this point. Go into the SmartThings IDE and choose "live logging" from the top menu.
   While on the live logging  screen and close to your hub with the sensor, press the button on the sensor, making sure it blinks, and watch the screen for results.
   You should see several lines of data come in slowly. This should tell you all your settings and what the configuration on the sensor is actually set to.
   It will then poll the sensors for data. If you do not ever see any data coming from the sensor, please post to the community or
   message me via github or the SmartThings community. 
 */
 
 metadata {
	definition (name: "zooZ 4-in-1 sensor - RV 1.2", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Motion Sensor"
		capability "Acceleration Sensor"
		capability "Temperature Measurement"
		capability "Relative Humidity Measurement"
		capability "Illuminance Measurement"
		capability "Configuration"
		capability "Sensor"
		capability "Battery"
		
        // RAW Description: 0 0 0x0701 0 0 0 e 0x5E 0x98 0x86 0x72 0x5A 0x85 0x59 0x73 0x80 0x71 0x31 0x70 0x84 0x7A   										 
		attribute "tamper", "enum", ["detected", "clear"]
		fingerprint deviceId: "0x0701", inClusters: "0x5E,0x86,0x72,0x59,0x85,0x73,0x71,0x84,0x80,0x31,0x70,0x5A,0x98,0x7A"
		}
/*	simulator {
		status "no motion" : "command: 9881, payload: 00300300"
		status "motion"    : "command: 9881, payload: 003003FF"
        status "clear" : " command: 9881, payload: 0071050000000007030000"
        status "tamper" : "command: 9881, payload: 007105000000FF07030000"
        
        for (int i = 0; i <= 100; i += 20) {
			status "temperature ${i}F": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().sensorMultilevelV2.sensorMultilevelReport(
                	scaledSensorValue: i,
                    precision: 2,
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
			status "illuminance ${i}%": new physicalgraph.zwave.Zwave().securityV1.securityMessageEncapsulation().encapsulate(
				new physicalgraph.zwave.Zwave().sensorMultilevelV2.sensorMultilevelReport(
                scaledSensorValue: i,
                sensorType: 3
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
*/
	tiles (scale: 2) {
		multiAttributeTile(name:"main", type:"generic", width:6, height:4) {
			tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
            	attributeState "temperature",label:'${currentValue}°', precision:2, backgroundColors:[
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
                attributeState "humidity",label:'RH ${currentValue} %', precision:2
            }
		}
        standardTile("motion","device.motion", width: 2, height: 2) {
            	state "active",label:'motion',icon:"st.motion.motion.active",backgroundColor:"#53a7c0"
                state "inactive",label:'no motion',icon:"st.motion.motion.inactive",backgroundColor:"#ffffff"
			}
		valueTile("humidity","device.humidity", width: 2, height: 2) {
           	state "humidity",label:'RH ${currentValue}%', precision:2
			}
		valueTile("illuminance","device.illuminance", width: 2, height: 2) {
            	state "luminosity",label:'LIGHT ${currentValue}%', precision:2
			}
		standardTile("acceleration", "device.acceleration", width: 2, height: 2) {
			state("active", label:'tamper', icon:"st.motion.acceleration.active", backgroundColor:"#ff0000")
			state("inactive", label:'clear', icon:"st.motion.acceleration.inactive", backgroundColor:"#00ff00")
		}
		valueTile("battery", "device.battery", decoration: "flat", width: 2, height: 2) {
			state "battery", label:'${currentValue}% battery'
		}
		main(["main"])
		details(["main","humidity","illuminance","motion","acceleration","battery"])
	}
    preferences {
		input "debugOutput", "boolean", 
			title: "Enable debug logging?",
			defaultValue: false,
			displayDuringSetup: true
		input "LEDbehavior", "enum",
			title: "LED Behavior",
			options: ["LED Off", "Breathing", "Quick Blink on Temp/PIR"],
            defaultValue: "Quick Blink on Temp/PIR",
			required: false,
			displayDuringSetup: false
		input "tempoffset", "number",
			title: "Reporting threshold  for temp",
            description: "Enter a value 1-50 changing reporting threshold for temp. Represents 0.1 degree increments.",
            range: "1..50",
			defaultValue: 50,
            required: false,
            displayDuringSetup: false
		input "humidityoffset", "number",
            title: "Reporting threshold for humidity",
            description: "Report when change occurs from 1%-50% RH)",
			range: "1..50",
			defaultValue: 1,
			required: false,
            displayDuringSetup: false
		input "luminanceoffset", "number",
            title: "Reporting threshold for Luminance",
            description: "valid values from 5% to 50%",
            range: "5..50",
			defaultValue: 5,
            required: false,
	        displayDuringSetup: false
		input "PIRsensitivity", "number",
    	    title: "PIR motion sensitivity",
			description: "A value from 1-7, from high to low sensitivity (1 is highest)",
			range: "1..7",
			defaultValue: 4,
			required: false,
			displayDuringSetup: true
		input "MotionReset", "number",
    	    title: "PIR reset time",
			description: "Number of minutes to wait to report motion cleared after a motion event if there is no motion detected.",
			range: "1..255",
			defaultValue: 5,
			required: false,
			displayDuringSetup: true            
	}
}

def updated()
{
	updateDataValue("configured", "false")
	state.debug = ("true" == debugOutput)
}

def parse(String description)
{
	def result = null
	if (description.startsWith("Err 106")) {
		state.sec = 0
		result = createEvent( name: "secureInclusion", value: "failed", isStateChange: true,
			descriptionText: "This sensor failed to complete the network security key exchange. If you are unable to control it via SmartThings, you must remove it from your network and add it again.")
	} else if (description != "updated") {
		def cmd = zwave.parse(description, [0x31: 5, 0x71:3, 0x7A: 2, 0x81: 1, 0x84: 2, 0x86: 2])
		if (cmd) {
			result = zwaveEvent(cmd)
		}
	}
	if (state.debug) log.debug "Parsed '${description}' to ${result.inspect()}"
	return result
}

def zwaveEvent(physicalgraph.zwave.commands.wakeupv2.WakeUpNotification cmd) 
{
	def result = [createEvent(descriptionText: "${device.displayName} woke up", isStateChange: false)]
	if (!isConfigured()) {
		// we're still in the process of configuring a newly joined device
		if (state.debug) log.debug("late configure")
		result += response(configure())
	} else {
		result += response(checkBattery())
	}
	result
}

def zwaveEvent(physicalgraph.zwave.commands.securityv1.SecurityMessageEncapsulation cmd) {
	def encapsulatedCommand = cmd.encapsulatedCommand([0x31: 5, 0x71: 3, 0x7A: 2, 0x81: 1, 0x84: 2])
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
	if (state.debug) log.debug "---SECURITY COMMANDS SUPPORTED REPORT V1--- ${device.displayName} sent commandClassControl: ${cmd.commandClassControl}, commandClassSupport: ${cmd.commandClassSupport}, reportsToFollow: ${cmd.reportsToFollow}"
	response(configure())
}

def zwaveEvent(physicalgraph.zwave.commands.manufacturerspecificv2.DeviceSpecificReport cmd) {
	if (state.debug) log.debug "---MANUFACTURER SPECIFIC REPORT V2--- ${device.displayName} sent deviceIdDataFormat: ${cmd.deviceIdDataFormat}, deviceIdDataLengthIndicator: ${cmd.deviceIdDataLengthIndicator}, deviceIdType: ${cmd.deviceIdType}, payload: ${cmd.payload}"
}

def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionCommandClassReport cmd) {
	if (state.debug) log.debug "---COMMAND CLASS VERSION REPORT V1--- ${device.displayName} has command class version: ${cmd.commandClassVersion} - payload: ${cmd.payload}"
}

def zwaveEvent(physicalgraph.zwave.commands.versionv1.VersionReport cmd) {
	def fw = "${cmd.applicationVersion}.${cmd.applicationSubVersion}"
	updateDataValue("fw", fw)
	if (state.debug) log.debug "---VERSION REPORT V1--- ${device.displayName} is running firmware version: $fw, Z-Wave version: ${cmd.zWaveProtocolVersion}.${cmd.zWaveProtocolSubVersion}"
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv1.ConfigurationReport cmd) {
    if (state.debug) log.debug "---CONFIGURATION REPORT V1--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
}

def zwaveEvent(physicalgraph.zwave.commands.configurationv2.ConfigurationReport cmd) {
    if (state.debug) log.debug "---CONFIGURATION REPORT V2--- ${device.displayName} parameter ${cmd.parameterNumber} with a byte size of ${cmd.size} is set to ${cmd.configurationValue}"
}

def zwaveEvent(physicalgraph.zwave.commands.batteryv1.BatteryReport cmd) {
	def map = [ name: "battery", unit: "%" ]
	if (cmd.batteryLevel == 0xFF) {
		map.value = 1
		map.descriptionText = "${device.displayName} battery is low"
		map.isStateChange = true
		createEvent(map)
	} else {
		map.value = cmd.batteryLevel
		createEvent(map)
	}
	state.lastbatt = now()
	createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv5.SensorMultilevelReport cmd) {
	if (state.debug) log.debug "---SENSOR MULTILEVEL v5 REPORT--- ${device.displayName} sent sensorType: ${cmd.sensorType} value: ${cmd.sensorValue} scale: ${cmd.scale} scaledSensorValue: ${cmd.scaledSensorValue}"
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
			map.value = cmd.scaledSensorValue
			map.unit = "%"
			break;
        case 5:
			map.name = "humidity"
			map.value = cmd.scaledSensorValue
			map.unit = "%"
			break;
		default:
			map.descriptionText = cmd.toString()
	}
	createEvent(map)
}

def motionEvent(value) {
	def map = [name: "motion"]
	if (value != 0) {
		map.value = "active"
		map.descriptionText = "$device.displayName detected motion"
	} else {
		map.value = "inactive"
		map.descriptionText = "$device.displayName motion has stopped"
	}
	createEvent(map)
}

def zwaveEvent(physicalgraph.zwave.commands.sensorbinaryv2.SensorBinaryReport cmd) {
	if (state.debug) log.debug "---SENSOR BINARY REPORT V2--- ${device.displayName} sent value: ${cmd.sensorValue}"
	motionEvent(cmd.sensorValue)
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd) {
	if (state.debug) log.debug "---BASIC SET REPORT V1--- ${device.displayName} sent value: ${cmd.value}"
	motionEvent(cmd.value)
}

def zwaveEvent(physicalgraph.zwave.commands.notificationv3.NotificationReport cmd) {
if (state.debug) log.debug "---NOTIFICATION REPORT V3--- ${device.displayName} sent eventParametersLength: ${cmd.eventParametersLength} eventParameter: ${cmd.eventParameter} notificationType: ${cmd.notificationType} event: ${cmd.event}"	
def result = []
if (cmd.notificationType == 7) {
	if (cmd.event == 0x00 && cmd.eventParameter == 0x08) {
		result << motionEvent(0)
        }
    else if (cmd.event == 0x03) {
    	result << createEvent(name: "acceleration", value: "active", descriptionText: "$device.displayName cover is open.")
		}
	else if (cmd.event == 0X00 || cmd.eventParameter == 0x03) {
		result << createEvent(name: "acceleration", value: "inactive", descriptionText: "$device.displayName cover has been closed.")
    }
	else if (cmd.event == 0x08) {
    	result << motionEvent(255)
	}
}
else {
	result << createEvent(descriptionText: cmd.toString(), isStateChange: false)
}
	result
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	createEvent(descriptionText: cmd.toString(), isStateChange: false)
}

private checkBattery() {
	def request = [
		zwave.batteryV1.batteryGet(),
		zwave.wakeUpV1.wakeUpNoMoreInformation()
		]
	commands(request)
}

def configure() {
	// This sensor joins as a secure device if you double-click the button to include it
	//if (device.device.rawDescription =~ /98/ && !state.sec) {
	//	if (state.debug) log.debug "4-in-1 sensor not sending configure until secure"
	//	return []
	//}
	if (state.debug) log.debug "--Sending configuration commands to zooZ 4-in-1 sensor--"
    //if (state.debug) log.debug "Prefernces settings: PIRsensitivity: $PIRsensitivity, Temp offset: $tempoffset, Humidity offset: $humidityoffset, Luminance offset: $luminanceoffset"
	def LEDbehav = 3
	if (LEDbehavior == "LED Off") {
    	LEDbehav=1
    }
	else if (LEDbehavior == "Breathing") {
    	LEDbehav=2
	}
	else {
		LEDbehav=3
	}	
	def PIRsens = 4
	if (PIRsensitivity) {
		PIRsens=PIRsensitivity
	}
	else {
		PIRsens = 4
	}
    def MotionRst = 3
	if (MotionReset) {
		MotionRst=MotionReset
	}
	else {
		MotionRst = 3
	}
	def tempoff = 1
	if (tempoffset) {
		tempoff=tempoffset
	}
	else {
		tempoff = 1
	}
	def humidityoff = 10
	if (humidityoffset) {
		humidityoff=humidityoffset
	}
	else {
		humidityoff = 10
	}
	def luminanceoff = 10
	if (luminanceoffset) {
		luminanceoff=luminanceoffset
	}
	else {
		luminanceoff = 10
	}
    if (state.debug) log.debug "settings: ${settings.inspect()}, state: ${state.inspect()}"
    setConfigured()
	def request = [
		// set wakeup interval to 20 mins
		zwave.wakeUpV1.wakeUpIntervalSet(seconds:3600, nodeid:zwaveHubNodeId),
		
		// Get Version information
        zwave.versionV1.versionGet(),
        zwave.firmwareUpdateMdV2.firmwareMdGet(),
		
        // configure temp scale to celcius or fahrenheight and set offset
		zwave.configurationV1.configurationSet(parameterNumber: 0x01, size: 1, scaledConfigurationValue: 1),
		zwave.configurationV1.configurationGet(parameterNumber: 0x01),
		zwave.configurationV1.configurationSet(parameterNumber: 0x02, size: 1, scaledConfigurationValue: tempoff),		
		zwave.configurationV1.configurationGet(parameterNumber: 0x02),
		
        // configure humidity offset
		zwave.configurationV1.configurationSet(parameterNumber: 0x03, size: 1, scaledConfigurationValue: humidityoff),
		zwave.configurationV1.configurationGet(parameterNumber: 0x03),
		
        // configure luminance offset
		zwave.configurationV1.configurationSet(parameterNumber: 0x04, size: 1, scaledConfigurationValue: luminanceoff),
		zwave.configurationV1.configurationGet(parameterNumber: 0x04),
		
		// send no-motion report x minutes after motion stops
		zwave.configurationV1.configurationSet(parameterNumber: 0x05, size: 1, scaledConfigurationValue: MotionRst),
		zwave.configurationV1.configurationGet(parameterNumber: 0x05),
    	
		// set motion sensor sensitivity
        zwave.configurationV1.configurationSet(parameterNumber: 0x06, size: 1, scaledConfigurationValue: PIRsens),
		zwave.configurationV1.configurationGet(parameterNumber: 0x06),
		
        // set LED behavior
        zwave.configurationV1.configurationSet(parameterNumber: 0x07, size: 1, scaledConfigurationValue: LEDbehav),
        zwave.configurationV1.configurationGet(parameterNumber: 0x07),
		
		// get updated battery and sensor data
        zwave.batteryV1.batteryGet(),
		zwave.sensorMultilevelV5.sensorMultilevelGet(sensorType:1, scale:1),
        zwave.sensorMultilevelV5.sensorMultilevelGet(sensorType:3, scale:0),
        zwave.sensorMultilevelV5.sensorMultilevelGet(sensorType:5, scale:0),
        
		// Can use the zwaveHubNodeId variable to add the hub to the device's associations:
		zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId)
    ]
	commands(request) + ["delay 20000", zwave.wakeUpV1.wakeUpNoMoreInformation().format()]
}

private setConfigured() {
	updateDataValue("configured", "true")
    return []
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

private commands(commands, delay=1000) {
	delayBetween(commands.collect{ command(it) }, delay)
}