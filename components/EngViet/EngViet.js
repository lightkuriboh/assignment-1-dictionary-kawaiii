import React from 'react';
import { StyleSheet, View, ScrollView, FlatList} from 'react-native';
import CircleButton from 'react-native-circle-button';
import { Button, Text, Form, Input, Item } from 'native-base';
import iconSound from '../../images/sound.png';
import iconDelete from '../../images/delete.png';
import iconInsert from '../../images/insert.png';
import iconUpdate from '../../images/update.png';
import Tts from 'react-native-tts';
Tts.setDefaultLanguage('en-IE');

import Menu from '../Menu';
import Document from '../DocumentTrans/DocumentTrans';

export default class EngViet extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            inputText: props.searchWord,
            myChosen: ''
        }
    }

    onTextChange = function (text) {
        this.setState({
            inputText: text
        });
        this.props.onTextChange(text);
    };

    speakSearchWord = () => {
        Tts.speak(this.props.searchWord);
    };

    madeChosen = (selection) => {
        this.setState({
           myChosen: selection
        });
        this.props.makeVisible();
    };

    render() {
        if (!this.props.isVisible) {
            return (
                <Menu
                    makeVisible={(myOption) => this.madeChosen(myOption)}
                />
            )
        } else if (this.state.myChosen === '1') {
            return (
                <Document
                    makeVisible = {this.props.makeVisible}
                />
            )
        }
        return (
            <View style = {styles.mainContainer}>
                <View style={styles.mainContent}>
                    <View style={styles.searchLayout}>
                        <View style={styles.searchForm}>
                            <Form>
                                <Item>
                                    <Input
                                        placeholder = "Search word"
                                        onChangeText={(text) => this.onTextChange(text)}
                                    />
                                </Item>
                                <Button block info small
                                        style={{width: '100%', height: '30%'}}
                                        onPress={this.props.onSearch}
                                        color="#841584"
                                >
                                    <Text>Submit</Text>
                                </Button>
                            </Form>
                        </View>
                        <View style={styles.listHints}>
                            <ScrollView>
                                <FlatList
                                    data = {
                                        this.props.hints
                                    }
                                    renderItem = {({item}) => <View style={styles.listContainer}><Button
                                        block
                                        success
                                        onPress={() => this.props.onChoose(item.key)}
                                        color = '#00cc00'
                                    ><Text>{item.key}</Text></Button></View>}
                                />
                            </ScrollView>
                        </View>
                    </View>
                    <View style = {styles.detailLayout}>
                        <View style={styles.detailPart}>
                            <ScrollView>
                                <Text>{this.props.result}</Text>
                            </ScrollView>
                        </View>
                        <View style={styles.usefulOptions}>
                            <CircleButton
                                iconButtonTop={iconSound}
                                iconButtonLeft={iconUpdate}
                                iconButtonRight={iconInsert}
                                iconButtonBottom={iconDelete}
                                onPressButtonTop={ () => {this.speakSearchWord()} }
                            />
                        </View>
                    </View>
                </View>
                <View style = {styles.footer}>
                    <Button rounded warning
                        onPress = {this.props.makeVisible}
                    >
                        <Text>Back</Text>
                    </Button>

                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    listOptions: {
        flex: 1,
        justifyContent: 'center',
        flexDirection: 'column',
        backgroundColor: 'red',
    },
    mainContainer: {
        flex: 1,
        justifyContent: 'center',
        backgroundColor: '#e6e6e6',
        padding: 10
    },
    buttonContainer: {
        padding: 10,
        justifyContent: 'center',
    },
    searchForm: {
        flex: 2,
        flexDirection: 'column',
        justifyContent: 'flex-start',
    },
    searchLayout: {
        flex: 2,
        flexDirection: 'column',
        justifyContent: 'flex-start',
        padding: 20,
        backgroundColor: '#e9e9e9',
        borderRadius: 15,
        margin: 10,
        borderWidth: 1,
        borderColor: '#0066ff'
    },
    detailLayout: {
        flex: 7,
        justifyContent: 'center',
        backgroundColor: '#e9e9e9',
        borderRadius: 15,
        margin: 10,
        borderWidth: 1,
        borderColor: '#0066ff',
        flexDirection: 'column'
    },
    footer: {
        flex: 0.5,
        flexDirection: 'row',
        justifyContent: 'center',
        zIndex: 0,
        paddingBottom: 5
    },
    mainContent: {
        flex: 9,
        justifyContent: 'center',
        flexDirection: 'row',
        zIndex: 1
    },
    listHints: {
        flex: 5,
        backgroundColor: 'white',
        marginTop: 10
    },
    listContainer: {
        margin: 2,
        justifyContent: 'center',
    },
    usefulOptions: {
        flex: 1,
        borderWidth: 1,
        borderColor: '#0066ff',
        justifyContent: 'space-between',
        flexDirection: 'row',
        borderRadius: 15,
    },
    detailPart: {
        flex: 10
    }
});