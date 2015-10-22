/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var {
  AppRegistry,
  Image,
  StyleSheet,
  Text,
  View,
  DrawerLayoutAndroid,
  ToolbarAndroid,
} = React;

var MainList = require('./MainList');
var toolbarActions = [
  {title:'notice',icon:require('image!ic_launcher'),show:'always'},
  {title:'setting',show:'never'},
];
var AwesomeProject = React.createClass({
  render:function() {
      var navigationView = (
          <View style = {styles.navigationView}>
            <Text style = {styles.ViewInDraw}> I am in the drawer!</Text>
          </View>
        );
      return (
          <DrawerLayoutAndroid
            drawerWidth = {300}
            drawerPosition={DrawerLayoutAndroid.positions.Left}
            renderNavigationView = {() => navigationView} 
            ref = {'drawer'}
             >
            <View style = {styles.container}> 
            <ToolbarAndroid 
                navIcon = {require('image!ic_launcher')} 
                titlecolor = 'white'
                title = 'actionbar'
                actions = {toolbarActions}
                onIconClicked = {() =>this.refs['drawer'].openDrawer()}
                style = {styles.toolbar}
                />
            <MainList />
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
    height: 100,
  },
  ViewInDraw:{
    margin:10,
    fontSize:15,
    textAlign:'left',
  },
});

AppRegistry.registerComponent('AwesomeProject', () => AwesomeProject);