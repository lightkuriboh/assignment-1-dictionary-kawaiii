import React from 'react';
import { StyleSheet, Text, View, TextInput, Button, ScrollView, FlatList} from 'react-native';

export default class EngViet extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        if (!this.props.isVisible) {
            return (
                <View>
                </View>
            )
        }
        return (
            <View/>
        );
    }
}

const styles = StyleSheet.create({

});