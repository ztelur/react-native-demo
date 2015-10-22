package com.awesomeproject.widget;

import com.facebook.react.uimanager.CatalystStylesDiffMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIProp;

/**
 * shanbay
 * Created by  tian.zhang@shanbay.com
 * date : 15-10-22.
 * time : 下午4:54
 */
public class PurchaseSuccessManager extends SimpleViewManager<PurchaseSuccessView> {
	public static final String REACT_NAME = "PurchaseView";

	@UIProp(UIProp.Type.NUMBER)
	public static final String PROP_LINE_WIDTH = "linewidth";
	@Override
	public String getName() {
		return REACT_NAME;
	}

	@Override
	protected PurchaseSuccessView createViewInstance(ThemedReactContext themedReactContext) {
		return new PurchaseSuccessView(themedReactContext);
	}

	@Override
	public void updateView(PurchaseSuccessView root, CatalystStylesDiffMap props) {
		super.updateView(root, props);
		if (props.hasKey(PROP_LINE_WIDTH)) {
			root.init(props.getInt(PROP_LINE_WIDTH,10));
		}
	}
}
