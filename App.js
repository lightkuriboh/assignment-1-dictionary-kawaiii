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
    Switch,
    Button
} from 'react-native';

import EngViet from './components/EngViet/EngViet';

const MyNativeModule = NativeModules.MyNativeModule;

type Props = {};
class App extends Component<Props> {

    componentWillMount() {
        this.setState({
            searchWord: 'Hieu dep trai',
            result: '',
            hints: [
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'},
                {key: 'beautiful'},
                {key: 'pretty'},
                {key: 'handsome'}
            ],
            details: {
                english: '',
                vietnamese: '',
                pronunciation: ''
            },
            renderingSearchForm: false,
            timePass: false
        });
        MyNativeModule.initData();
    };

    onTextChange = (text) => {
        this.setState({
            searchWord: text
        })
    };

    greetUserCallBack = () => {
        const state = this.state;
        MyNativeModule.greetUser(state.searchWord, this.displayResult);
    };

    displayResult = (result) => {
        this.setState({
            result: result
        });
    };

    onChooseWord = (word) => {
        this.setState({
            searchWord: word
        });
        this.greetUserCallBack();
    };

    makeSearchFormVisible = () => {
        this.setState({
            renderingSearchForm: !this.state.renderingSearchForm
        })
    };

    render() {
        return (
            <View style={styles.container}>
                <View style={
                    {
                        flex: 1,
                        flexDirection: 'column',
                        width: '100%'
                    }
                }>
                    <View style={
                        {
                            flex: 0.85,
                            backgroundColor: '#222',
                            width: '100%',
                            justifyContent: 'center',
                        }
                    }>
                        <Text style={styles.heading}>
                            Super Dictionary
                        </Text>
                    </View>
                    <View style={
                        {
                            flex: 5,
                            backgroundColor: '#f5fcff',
                            width: '100%',
                            justifyContent: 'center'
                        }
                    }>
                        <EngViet
                            onTextChange = {(text) => this.onTextChange(text)}
                            onSearch = {() => this.greetUserCallBack()}
                            onChoose = {(word) => this.onChooseWord(word)}
                            makeVisible = {() => this.makeSearchFormVisible()}
                            text = {this.state.searchWord}
                            details = {this.state.details}
                            hints = {this.state.hints}
                            isVisible = {this.state.renderingSearchForm}
                            result = {this.state.result}
                        />
                        <Text>{this.state.searchWord}</Text>
                    </View>
                    <View style={
                        {
                            flex: 0.5,
                            backgroundColor: '#adebeb',
                            width: '100%',
                            justifyContent: 'center',
                        }
                    }>
                        <Text style={styles.about}>
                            This UI designed by KuribohKute
                        </Text>
                    </View>
                </View>
            </View>
        );
    }
}

export default App;

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    heading: {
        textAlign: 'center',
        color: 'white',
        fontSize: 20,
        fontWeight: 'bold'
    },
    about: {
        textAlign: 'center',
        color: 'black',
        fontSize: 15,
        fontStyle: 'italic'
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    buttonContainer: {
        margin: 10,
        justifyContent: 'center'
    }
});
