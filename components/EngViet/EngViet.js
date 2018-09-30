import React from 'react';
import { StyleSheet, View, ScrollView, FlatList} from 'react-native';
import CircleButton from 'react-native-circle-button';
import { Button, Text, Form, Input, Item, Card, CardItem, Icon } from 'native-base';
import iconSound from '../../images/sound.png';
import iconDelete from '../../images/delete.png';
import iconInsert from '../../images/insert.png';
import iconUpdate from '../../images/update.png';
import Tts from 'react-native-tts';
Tts.setDefaultLanguage('en-IE');

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

    render() {
        return (
            <View style = {styles.mainContainer}>
                <View style={styles.mainContent}>
                    <View style={styles.searchLayout}>
                        <View style={styles.searchForm}>
                            <Form>
                                <Item>
                                    <Input
                                        bordered
                                        placeholder = "Search"
                                        onChangeText={(text) => this.onTextChange(text)}
                                    />
                                    <Button transparent onPress={this.props.onSearch}>
                                        <Icon name='search' />
                                    </Button>
                                </Item>
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
                        <Card>
                            <CardItem header bordered>
                                <Text>
                                    Word Details
                                </Text>
                            </CardItem>
                            <CardItem cardBody style={{height:'70%'}}>
                                <ScrollView>
                                    <Text>{this.props.result}</Text>
                                </ScrollView>
                            </CardItem>
                            <CardItem footer bordered style={styles.usefulOptions}>
                                <CircleButton
                                    iconButtonTop={iconSound}
                                    iconButtonLeft={iconUpdate}
                                    iconButtonRight={iconInsert}
                                    iconButtonBottom={iconDelete}
                                    onPressButtonTop={ () => {this.speakSearchWord()} }
                                />
                            </CardItem>
                        </Card>
                    </View>
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
        height: 60,
        flexDirection: 'column',
        justifyContent: 'flex-start',
    },
    searchLayout: {
        flex: 1.25,
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
        flexDirection: 'column'
    },
    mainContent: {
        flex: 9,
        justifyContent: 'center',
        flexDirection: 'row',
        zIndex: 1
    },
    listHints: {
        flex: 1,
        backgroundColor: 'white',
        marginTop: 10
    },
    listContainer: {
        margin: 2,
        justifyContent: 'center',
    },
    usefulOptions: {
        justifyContent: 'space-between',
        flexDirection: 'row',
        borderRadius: 15,
    },
    detailPart: {
        flex: 10
    }
});