package com.awesomeproject;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * shanbay
 * Created by  tian.zhang@shanbay.com
 * date : 15-10-22.
 * time : 下午4:37
 */
public class ModulePackage implements ReactPackage {
	@Override
	public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
		List<NativeModule> modules = new ArrayList<NativeModule>();
		return modules;
	}

	@Override
	public List<Class<? extends JavaScriptModule>> createJSModules() {
		List<Class<? extends JavaScriptModule>> modules = new ArrayList<Class<? extends JavaScriptModule>>();
		return modules;
	}

	@Override
	public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
		List<ViewManager> list = new ArrayList<ViewManager>();
		return list;
	}
}
