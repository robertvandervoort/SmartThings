/*
 * V 1.1 of zooZ motion sensor ZSE-02 code 5/13/16
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
	definition (name: "zooZ motion sensor ZSE-02 - RV 1.1", namespace: "robertvandervoort", author: "Robert Vandervoort") {
		capability "Motion Sensor"
		capability "Acceleration Sensor"
		capability "Sensor"
		capability "Battery"
		
        // RAW Description: 0 0 0x0701 0 0 0 a 0x5E 0x85 0x59 0x71 0x80 0x5A 0x73 0x84 0x72 0x86
		attribute "tamper", "enum", ["detected", "clear"]
		fingerprint deviceId: "0x0701", inClusters: "0x5E 0x85 0x59 0x71 0x80 0x5A 0x73 0x84 0x72 0x86"
		}
simulator {
		status "no motion" : "command: 9881, payload: 00300300"
		status "motion"    : "command: 9881, payload: 003003FF"
        status "clear" : " command: 9881, payload: 0071050000000007030000"
        status "tamper" : "command: 9881, payload: 007105000000FF07030000"
        
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
		multiAttributeTile(name:"main", type: "generic", width: 6, height: 4){
			tileAttribute ("device.motion", key: "PRIMARY_CONTROL") {
				attributeState "active", label:'motion', icon:"st.motion.motion.active", backgroundColor:"#53a7c0"
				attributeState "inactive", label:'no motion', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff"
			}
		}
		standardTile("acceleration", "device.acceleration", width: 2, height: 2) {
			state("active", label:'DISABLED', backgroundColor:"#ff0000")
			state("inactive", label:'ENABLED', backgroundColor:"#00ff00")
		}
		valueTile("battery", "device.battery", decoration: "flat", width: 2, height: 2) {
			state "battery", label:'${currentValue}% battery', unit:""
		}
		main(["main"])
		details(["main","acceleration","battery"])
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
	updateDataValue("configured", "false")
	state.debug = ("true" == debugOutput)
}

def parse(String description)
{
	def result = null
	if (description != "updated") {  
		def cmd = zwave.parse(description, [0x31: 5, 0x71:3, 0x7A: 2, 0x81: 1, 0x84: 2, 0x86: 1])
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
		result += response(zwave.wakeUpV1.wakeUpNoMoreInformation())
	}
	result
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

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicSet cmd) {
	if (state.debug) log.debug "---BASIC SET REPORT V1--- ${device.displayName} sent value: ${cmd.value}"
	motionEvent(cmd.value)
}

def zwaveEvent(physicalgraph.zwave.commands.notificationv3.NotificationReport cmd) {
if (state.debug) log.debug "---NOTIFICATION REPORT V3--- ${device.displayName} sent eventParametersLength: ${cmd.eventParametersLength} eventParameter: ${cmd.eventParameter} notificationType: ${cmd.notificationType} event: ${cmd.event}"	
def result = []
if (cmd.notificationType == 7) {
	if (cmd.eventParameter == [8] && cmd.event == 0x00) {
		result << motionEvent(0)
        }
    else if (cmd.event == 0x03) {
    	result << createEvent(name: "acceleration", value: "active", descriptionText: "$device.displayName has been deactivated by the switch.")
		}
	else if (cmd.event == 0X00 || cmd.eventParameter == 0x03) {
		result << createEvent(name: "acceleration", value: "inactive", descriptionText: "$device.displayName has been reactivated by the switch.")
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

def configure() {
	if (state.debug) log.debug "--Sending configuration commands to zooZ motion sensor--"
    if (state.debug) log.debug "settings: ${settings.inspect()}, state: ${state.inspect()}"
    setConfigured()
	def request = [
		// set wakeup interval to 20 mins
		zwave.wakeUpV1.wakeUpIntervalSet(seconds:1200, nodeid:zwaveHubNodeId),
		
		// Can use the zwaveHubNodeId variable to add the hub to the device's associations:
		zwave.associationV1.associationSet(groupingIdentifier:2, nodeId:zwaveHubNodeId),
		
		//Get association
		zwave.associationV1.associationGet(groupingIdentifier:1),
        zwave.associationV1.associationGet(groupingIdentifier:2),

		// Get Version information
        zwave.versionV1.versionGet(),

		// get updated battery and sensor data
        zwave.batteryV1.batteryGet(),
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
	cmd.format()
}

private commands(commands, delay=1000) {
	delayBetween(commands.collect{ command(it) }, delay)
}