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
 	Image,
 } = React;
// var PurchaseSuccessView = require('./PurchaseSuccessView');

 var DetailScreen = React.createClass({
 	render: function  () {
 		var data = this.props.data;
 		return (
 			<View style = {styles.container}>
 				<Text style = {styles.title}>{data.title} </Text>
 				<Image
          				source={{uri:data.posters.thumbnail}}
          				style={styles.thumbnail}/>
 				<TextInput  defaultValue = {'dddddd'}/>
 				// <PurchaseSuccessView />
 			</View>
 			);
 	},
 });
var styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
  },
  purchaseview: {
  	width:50,
  	height:50,
  },
  title: {
    fontSize: 20,
    marginBottom: 8,
    textAlign: 'center',
  },
  textinput: {
  	height:40,
  	borderColor:'gray',
  	borderWidth:20
  },
    thumbnail: {
    width: 53,
    height: 81,
  },
});

module.exports = DetailScreen;
