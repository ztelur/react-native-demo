'use strict';
/*
 * tian.zhang
 * 10 22
 */

 var {requireNativeComponet} = require('react-native');
 var PurchaseSuccessView = {
 	name: 'PurchaseView',
 	propTypes: {
 		linewidth:PropTypes.Number,
 	}
 }
 module.exports = requireNativeComponet('PurchaseView',PurchaseSuccessView);