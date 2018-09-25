/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    NativeModules,
    TextInput,
    Switch
} from 'react-native';

import Button from 'react-native-button'

const MyNativeModule = NativeModules.MyNativeModule;

const instructions = Platform.select({
    ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
    android:
        'Double tap R on your keyboard to reload,\n' +
        'Shake or press menu button for dev menu',
    });

type Props = {};
class App extends Component<Props> {

    state = {
        greetingMessage: undefined,
        userName: undefined
    }

    componentWillMount() {
        this.setState({
            greetingMessage: 'minh sieu ngu ngu',
            userName: 'dep trai qua'
        });
        MyNativeModule.initData();
    }

    greetUserCallBack = () => {
        const state = this.state;
        MyNativeModule.greetUser(state.userName, this.displayResult);
    }

    displayResult = (result) => {
        this.refs.userName.blur();
        this.setState({
            greetingMessage: result
        })
    }

    render() {
        return (
            <View style={styles.container}>
                <TextInput
                    ref = "userName"
                    autoCorrect={false}
                    placeholder = "user name"
                    onChangeText = {(text) => this.setState({userName: text})}
                />
                <Text>
                    good not good
                </Text>
                <Button
                    containerStyle={styles.buttonRow}
                    onPress={this.greetUserCallBack}
                >
                    Greet (callback) {this.state.userName}
                </Button>
                <View style={styles.flexWrap}>
                    <Text> Response: </Text>
                    <Text>{this.state.greetingMessage}</Text>
                </View>
            </View>
        );
    }
}

export default App;

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});
