/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  View,
  Navigator,
  BackAndroid,
} = React;

// sel-defined component
var MainScreen = require('./MainScreen');

var _navigator;
/** backpress button **/
// BackAndroid.addEventListener('hardwareBackPress',function () {
//   if (_navigator && _navigator.getCurrentRoutes().length >1) { // if length >1 means not in mainPage 
//     _navigator.pop();
//     return true;
//   } else {
//     return false;
//   }
// });
var AwesomeProject = React.createClass({
  // RouteMapper: function  (route,navigationOperations,onComponetRef) {
  //   _navigator = navigationOperations;
  //   if (route.name === 'home') {
  //     return (
  //       <View style = {styles.container} >
  //         <MainScreen navigator = {navigationOperations}/>
  //       </View>
  //       );
  //   } else if (route.name === 'detail') {
  //     return ( <View style = {styles.container} >

  //     </View>);
  //   }
  // },
  render:function() {
    // var initialRoute = {name:'home'};
    return (
        // <Navigator 
        //     style  = {styles.container}
        //     initialRoute = {initialRoute}
        //     configureScene={() => Navigator.SceneConfigs.FadeAndroid}
        //     renderScene={this.RouteMapper} />
        <MainScreen/>
      );
  },
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

AppRegistry.registerComponent('AwesomeProject', () => AwesomeProject);