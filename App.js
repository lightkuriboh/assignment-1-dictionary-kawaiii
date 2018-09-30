/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import Menu from './components/Menu';
import {Header, Left, Body, Button, Icon} from 'native-base';

import {
    StyleSheet,
    Text,
    View,
    NativeModules
} from 'react-native';

const MyNativeModule = NativeModules.MyNativeModule;

type Props = {};
class App extends Component<Props> {

    componentWillMount() {
        this.setState({
            searchWord: 'Hieu dep trai',
            result: '',
            showingMenu: true,
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
            renderingSearchForm: false
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
        MyNativeModule.greetUser(word, this.displayResult);
        this.setState({
            searchWord: word
        });
    };

    makeMenuVanish = () => {
        this.setState({
            showingMenu: false
        });
    };

    makeMenuVisible = () => {
        this.setState({
            showingMenu: true
        });
    };

    render() {
        return (
            <View style={styles.container}>
                <View style={
                    {
                        flex: 0.5,
                        backgroundColor: '#0066ff',
                        width: '100%',
                        justifyContent: 'space-between',
                        flexDirection: 'row'
                    }
                }>
                    <View style={{justifyContent: 'center'}}>
                        <Button transparent onPress={() => this.makeMenuVisible()}>
                            <Icon name='arrow-back' style={{color: 'white'}} />
                        </Button>
                    </View>
                    <View style={{justifyContent: 'center'}}>
                        <Text style={styles.heading}>
                            Super Dictionary
                        </Text>
                    </View>
                    <View style={{justifyContent: 'center'}}>
                        <Button transparent>
                            <Icon name='heart' style={{color: 'white'}} />
                        </Button>
                    </View>
                </View>
                <View style={
                    {
                        flex: 5,
                        backgroundColor: '#f5fcff',
                        width: '100%',
                        justifyContent: 'center',
                        flexDirection: 'row'
                    }
                }>
                    <Menu
                        onTextChange = {(text) => this.onTextChange(text)}
                        onSearch = {() => this.greetUserCallBack()}
                        onChoose = {(word) => this.onChooseWord(word)}
                        searchWord = {this.state.searchWord}
                        details = {this.state.details}
                        hints = {this.state.hints}
                        result = {this.state.result}
                        showingMenu = {this.state.showingMenu}
                        makeMenuVanish = {this.makeMenuVanish}
                    />
                </View>
                <View style={
                    {
                        flex: 0.3,
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
        flexDirection: 'column'
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
