/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import Menu from './components/Menu';
import {Button, Icon} from 'native-base';

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
            searchWord: '',
            result: '',
            showingMenu: true,
            hints: [],
            renderingSearchForm: false,
            deleteActionResult: '',
            insertActionResult: '',
            updateActionResult: ''
        });
        MyNativeModule.initData();
    };

    greetUserCallBack = () => {
        const state = this.state;
        MyNativeModule.greetUser(state.searchWord, this.displayResult);
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
                        searchWord = {this.state.searchWord}
                        showingMenu = {this.state.showingMenu}
                        makeMenuVanish = {this.makeMenuVanish}
                        deleteResult = {this.state.deleteActionResult}

                        core = {MyNativeModule}
                    />
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
