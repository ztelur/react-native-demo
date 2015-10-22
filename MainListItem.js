'use strict';
/*
 * tian.zhang 
 * 10 22
 */
var React = require('react-native');

var {
	StyleSheet,
	Image,
	Text,
	View,
} = React;

var MainListItem = React.createClass({
	render:function  () {
		var movie = this.props.movie;
		return (      
			<View style={styles.container}>
        				<Image
          				source={{uri: movie.posters.thumbnail}}
          				style={styles.thumbnail}/>
        				<View style={styles.rightContainer}>
          				<Text style={styles.title}>{movie.title}</Text>
          				<Text style={styles.year}>{movie.year}</Text>
        				</View>
      			</View>);
	},
});

var styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  rightContainer: {
    flex: 1,
  },
  title: {
    fontSize: 20,
    marginBottom: 8,
    textAlign: 'center',
  },
  year: {
    textAlign: 'center',
  },
  thumbnail: {
    width: 53,
    height: 81,
  },
  listView: {
    paddingTop: 20,
    backgroundColor: '#F5FCFF',
  },
});

module.exports = MainListItem;