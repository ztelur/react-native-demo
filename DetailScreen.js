'use strict';

/** 
 * tian.zhang
 * 10 22
 */
 var React = require('react-native');

 var {
 	View,
 	Text,
 	ScrollView,
 	StyleSheet,
 	TextInput,
 } = React;

 var DetailScreen = React.createClass({
 	getInitialState: function  () {
 		return ({
 			isLoading :false,
 			detail : null,
 			scrollY:0,

 		});
 	},
 	render: function  () {
 		return (
 			<View style = {styles.container}>
 			<Text > this.props.data.title </Text>
 			<TextInput />
 			</View>
 			);
 	}
 });
var styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
