'use strict';
/*
 * tian.zhang
 * 10 26
 */
 var React = require('react-native');

 var {
 	Text,
 	View,
 	TextInput,
 	Image,
 	StyleSheet,
 } = React;

var Button = require('./widget/button/button');

 var CompleScreen = React.createClass({
 	getInitialState:function  () {
 		return ({
 			matchRate:0,
 			mode:'hard',
 		});
 	},
 	show:function  (date) {
 		console.log(data);
 	},
 	render:function  () {
 		var show = 'visible';
 		if (this.state.matchRate == 100) {
 			console.log("hidden" + this.state.matchRate);
 			show = 'hidden';
 		}
 		return (<View style = {[styles.container,{backfaceVisibility:show}]}>
 				<View style = {styles.content}>
 					<Text style = {styles.word}> {'acquire'}</Text>
 			
 			 		<Text style = {styles.translation}> {'when her mother advice her to leave,Alex are willingly to stay'}</Text>

 			 		<Image 
 			 			style = {styles.divider}
 			 			source = {require('image!icon_day_line')}/>

 			 		<TextInput 
 			 			style = {styles.input}
 			 			defaultValue = {'input word here'}/>
 			 		<Text style = {styles.matchRate}>
 			 			{'matchRate:' + this.state.matchRate}
 			 		</Text>
 				</View>
 			 	<View style  = {styles.hintContainer}>
 			 		<Image style = {styles.hintImage}
 			 			source = {'image!hint'}/>
 			 		<Text style = {styles.hintText}>
 			 			{'hint'}
 			 		</Text>
 			 	</View>
 			 	<Button style = {styles.nextSentence} 
 			 		onPress = {this._handlePress}>
 			 		"next sentence"
 			 	</Button>

 			</View>
 			);
 	},
 	_handlePress(event) {
 		var pre = this.state.matchRate + 10;
 		this.setState({matchRate:pre});
 		console.log('pressed');
 	}
 });

var styles = StyleSheet.create({
 container:{
 	flex:1,
 	backgroundColor: '#F5FCFF',
 },
 content:{
 	flex:1,
 	backgroundColor:'#ff00ff',
 	flexDirection:'column',
 	alignItems:'flex-start',

 },
 divider:{
 	alignSelf:'center',
 	backgroundColor: '#F5FCFF',
 },
 input:{
 	height:100,
 	borderColor:'gray',
 	borderWidth:1,
 },
 matchRate:{
 	alignSelf:'flex-end',
 	marginRight:20,
 },
 hintContainer:{
 	flex:1,
 	justifyContent:'flex-start'
 },
 hintImage:{
 	width:50,
 	height:50,
 },
 hintText:{

 },
 nextSentence:{
 	width:200,
 	height:50,
 	alignSelf:'center',
 	backgroundColor:'#7ec9ac'
 },
 word:{
 	fontSize:20,
 	color:'#f1e050',
 	backgroundColor:'00ff00',
 	alignSelf:'flex-end',
 },
 translation:{
 	fontSize:25,
 	color:'#0033ee',
 	backgroundColor:'ffffff',
 },

});

module.exports = CompleScreen;