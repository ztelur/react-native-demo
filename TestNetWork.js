'use strict';
/*
 * tian.zhang
 * 10 26
 */

 var React = require('react-native');
 var {
 	Text,
 	View
 } = React;

var URL = '';
 var TestNetWork = React.createClass({
 	propTypes: {
 		url:propTypes.string,
 		method:propTypes.string,
 	},
 	getInitialState:function  () {
 		return ({
 			isLoading:false,
 			detail:null,

 		});
 	}
 	testNetWork:function  () {
 		fetch(URL)
 		  .then((response) => response.json())
 		  .catch((error) =>{
 		  	
 		  })
 		  .then((responseData) => {
 		  	this.setState({
 		  		isLoading:false,
 		  		detail:responseData,
 		  	});
 		  })
 		  .done();
 	}
 	testNetWorkPost:function  () {
 		var form = {};
 		fetch(URL,{method:'post',body:new FormData(form)})
 		  .then((response) => response.json())
 		  .catch((error) =>{

 		  })
 		  .then((responseData) => {
 		  	this.setState({
 		  		isLoading:false,
 		  		detail:responseData,
 		  	});
 		  })
 		  .done();
 	}
 	render:function() {
 		if (this.state.isLoading) {
 			return (
 				<View ><Text>"loading"</Text></View>
 				);
 		} else {
 			if (this.state.detail) {
 				<View ><Text>"success"</Text></View> 				
 			}
 		}
 	}
 });
 module.exports = TestNetWork;