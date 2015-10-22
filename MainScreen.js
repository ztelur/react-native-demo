'use strict';
/**
 * tian.zhang
 * 10 22
 */

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  View,
  DrawerLayoutAndroid,
  ToolbarAndroid,
  Text
} = React;

var toolbarActions = [
  {title:'notice',icon:require('image!ic_launcher'),show:'always'},
  {title:'setting',show:'never'},
];

// self-defined component
var MainList = require('./MainList');

var MainScreen = React.createClass({
  render:function() {
      var navigationView = (
          <View style = {styles.navigationView}>
            <Text style = {styles.ViewInDraw}> I am in the drawer!</Text>
          </View>
        );
      return (
          <DrawerLayoutAndroid
            drawerWidth = {90}
            drawerPosition={DrawerLayoutAndroid.positions.Left}
            renderNavigationView = {() => navigationView} 
            ref = {'drawer'}>
            <View style = {styles.container}> 
            <ToolbarAndroid 
                navIcon = {require('image!ic_launcher')} 
                titlecolor = 'white'
                title = 'actionbar'
                actions = {toolbarActions}
                onIconClicked = {() =>{
                	// this.refs['drawer'].openDrawer()}
                	var navigator = this.props.navigator;
                	navigator.push({
                		title : 'ddd',
  			name: 'detail',
  			data: {title:"dddd",year:'1023',posters:{thumbnail:'xxxxxx'}},
                	});
                }}
                style = {styles.toolbar}/>
            <MainList  navigator = {this.props.navigator}/>
            </View>
            </DrawerLayoutAndroid>
        );
  },
});

var styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: '#FAFAFA',
  },
  navigationView: {
    flex:1,
    backgroundColor:'#00ff00',
  },
    toolbar: {
    backgroundColor: '#00a2ed',
    height: 50,
  },
  ViewInDraw:{
    margin:10,
    fontSize:15,
    textAlign:'left',
  },
});
module.exports = MainScreen;