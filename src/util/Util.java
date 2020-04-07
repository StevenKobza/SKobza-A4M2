package util;

import storageClasses.*;

import java.awt.Color;
import java.util.ArrayList;

public class Util {

	public static float random(int low, int high) {
		return (float)(Math.random()*high + low);
	}
	
	public static void setUpMenus(ArrayList<SettingMenu> settings) {
		
		
		SettingMenu airplaneMode = new SettingMenu("Airplane Mode",1);
		
		SettingMenu wifi = new SettingMenu("Wi-Fi",1);
		SettingMenu wifi2 = new SettingMenu("Wi-Fi", 1);
		SettingMenu askToJoin = new SettingMenu("Ask To Join Networks", 2);
		SettingMenu autoJoin = new SettingMenu("Auto-Join Hotspot", 3);
		wifi.addSubSetting(wifi2);
		wifi.addSubSetting(askToJoin);
		wifi.addSubSetting(autoJoin);
		
		SettingMenu bluetooth = new SettingMenu("Bluetooth",1);
		SettingMenu bt = new SettingMenu("Bluetooth", 1);
		bluetooth.addSubSetting(bt);
		
		SettingMenu cellular = new SettingMenu("Cellular",1);
		cellular.addSubSetting(new SettingMenu("Cellular Data", 1));
		cellular.addSubSetting(new SettingMenu("Cellular Data Options", 1));
		cellular.addSubSetting(new SettingMenu("Personal Hotspot", 1));
		cellular.addSubSetting(new SettingMenu("Wi-Fi Calling", 2));
		cellular.addSubSetting(new SettingMenu("Call on Other Devices", 2));
		cellular.addSubSetting(new SettingMenu("Carrier Services", 2));
		cellular.addSubSetting(new SettingMenu("Network Selection", 2));
		cellular.addSubSetting(new SettingMenu("SIM PIN", 2));
		cellular.addSubSetting(new SettingMenu("SIM Applications", 2));
		cellular.addSubSetting(new SettingMenu("Wi-Fi Assist", 3));
		cellular.addSubSetting(new SettingMenu("iCloud Drive", 4));
		
		SettingMenu personalHotspot = new SettingMenu("Personal Hotspot",1);
		SettingMenu vpn = new SettingMenu("VPN",1);
		
		settings.add(airplaneMode);
		settings.add(wifi);
		settings.add(bluetooth);
		settings.add(cellular);
		settings.add(personalHotspot);
		settings.add(vpn);
		
		
		SettingMenu notif = new SettingMenu("Notifications",2);
		SettingMenu soundHap = new SettingMenu("Sounds & Haptics",2);
		SettingMenu dnd = new SettingMenu("Do Not Disturb",2);
		SettingMenu screenTime = new SettingMenu("Screen Time",2);
		
		settings.add(notif);
		settings.add(soundHap);
		settings.add(dnd);
		settings.add(screenTime);
		
		
		SettingMenu general = new SettingMenu("General",3);
		SettingMenu cntrlCenter = new SettingMenu("Control Center",3);
		SettingMenu disp = new SettingMenu("Display & Brightness",3);
		SettingMenu access = new SettingMenu("Accessibility",3);
		SettingMenu wall = new SettingMenu("Wallpaper",3);
		SettingMenu wall1 = new SettingMenu("Photo 1", 1);
		SettingMenu wall2 = new SettingMenu("Photo 2", 2);
		SettingMenu wall3 = new SettingMenu("Photo 3", 3);
		SettingMenu wall4 = new SettingMenu("Keep Current", 4);
		
		wall.addSubSetting(wall1);
		wall.addSubSetting(wall2);
		wall.addSubSetting(wall3);
		wall.addSubSetting(wall4);
		
		SettingMenu siriS = new SettingMenu("Siri & Search",3);
		SettingMenu faceID = new SettingMenu("Face ID & Passcode",3);
		SettingMenu emerg = new SettingMenu("Emergency SOS",3);
		SettingMenu batt = new SettingMenu("Battery",3);
		SettingMenu priv = new SettingMenu("Privacy",3);
		
		settings.add(general);
		settings.add(cntrlCenter);
		settings.add(disp);
		settings.add(access);
		settings.add(wall);
		settings.add(siriS);
		settings.add(faceID);
		settings.add(emerg);
		settings.add(batt);
		settings.add(priv);
		
		
		SettingMenu itunes = new SettingMenu("iTunes & App Store",4);
		SettingMenu wallet = new SettingMenu("Wallet & Apple Pay",4);
		
		settings.add(itunes);
		settings.add(wallet);
		
		
		SettingMenu pw = new SettingMenu("Passwords & Accounts",5);
		SettingMenu mail = new SettingMenu("Mail",5);
		SettingMenu contacts = new SettingMenu("Contacts",5);
		SettingMenu cale = new SettingMenu ("Calendar",5);
		SettingMenu notes = new SettingMenu("Notes",5);
		SettingMenu reminders = new SettingMenu ("Reminders",5);
		SettingMenu vm = new SettingMenu("Voice Memos",5);
		SettingMenu phone = new SettingMenu("Phone",5);
		SettingMenu messages = new SettingMenu("Messages",5);
		SettingMenu ft = new SettingMenu ("FaceTime",5);
		SettingMenu maps = new SettingMenu ("Maps",5);
		SettingMenu compass = new SettingMenu("Compass",5);
		SettingMenu measure = new SettingMenu("Measure",5);
		SettingMenu safari = new SettingMenu("Safari",5);
		SettingMenu news = new SettingMenu("News",5);
		SettingMenu stocks = new SettingMenu("Stocks",5);
		SettingMenu health = new SettingMenu("Health",5);
		SettingMenu shortCuts = new SettingMenu("Shortcuts",5);

		settings.add(pw);
		settings.add(mail);
		settings.add(contacts);
		settings.add(cale);
		settings.add(notes);
		settings.add(reminders);
		settings.add(vm);
		settings.add(phone);
		settings.add(messages);
		settings.add(ft);
		settings.add(maps);
		settings.add(compass);
		settings.add(measure);
		settings.add(safari);
		settings.add(news);
		settings.add(stocks);
		settings.add(health);
		settings.add(shortCuts);
		
		
		SettingMenu music = new SettingMenu("Music",6);
		SettingMenu tv = new SettingMenu("TV",6);
		SettingMenu photos = new SettingMenu("Photos",6);
		SettingMenu camera = new SettingMenu("Camera",6);
		SettingMenu podcasts = new SettingMenu("Podcasts",6);
		SettingMenu gc = new SettingMenu("Game Center",6);
		
		settings.add(music);
		settings.add(tv);
		settings.add(photos);
		settings.add(camera);
		settings.add(podcasts);
		settings.add(gc);
	}
	
	public static void setUpClasses(ArrayList<Classes> classes) {
		Classes iat106 = new Classes("IAT 106 Line Drawing Simulator", "IAT 106", Color.blue, (500-100)/2, 100);
		Classes iat201 = new Classes("IAT 201 Working with groups ain't great", "IAT 201", Color.red, (500-100)/2, 100);
		Classes iat265 = new Classes("IAT 106 Line Drawing Simulator", "IAT 265", Color.blue, (500-100)/2, 100);
		Classes iat340 = new Classes("IAT 201 Working with groups ain't great", "IAT 340", Color.red, (500-100)/2, 100);
		classes.add(iat106);
		classes.add(iat201);
		classes.add(iat265);
		classes.add(iat340);
	}
}
