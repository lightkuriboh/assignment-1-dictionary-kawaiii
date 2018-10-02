import React from 'react';
import { StyleSheet, View, ScrollView, FlatList} from 'react-native';
import { Button, Text, Form, Input, Item, Card, CardItem, Icon } from 'native-base';
import Prompt from 'react-native-input-prompt';
import tts from 'react-native-tts';

export default class EngViet extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            inputText: props.searchWord,
            myChosen: '',
            insertVisible: false,
            deleteVisible: false,
            updateVisible: false
        };
    }

    onTextChange = function (text) {
        this.setState({
            inputText: text
        });
        this.props.onTextChange(text);
    };

    speakSearchWord = () => {
        alert('fuck');
        tts.getInitStatus().then(() => {
            tts.speak(this.props.searchWord);
        }, (err) => {
            if (err.code === 'no_engine') {
                tts.requestInstallEngine();
            }
        });
    };

    itemWordChosen = (word) => {
        this.setState({
            inputText: word
        });
        this.props.onChoose(word);
    };

    confirmDeleteWord = (word) => {
        this.setState({
            deleteVisible: false
        });
        if (word.toLowerCase() === "y") {
            let myWord = this.props.searchWord;
            let that = this;
            setTimeout(function(){that.props.onDelete(myWord)}, 1000);
            alert(this.props.deleteResult);
        }
    };

    render() {
        return (
            <View style = {styles.mainContainer}>
                <Prompt
                    title={'Delete This Word'}
                    visible={this.state.deleteVisible}
                    placeholder={"y/n"}
                    onSubmit={(text) => this.confirmDeleteWord(text)}
                    onCancel={() => this.setState({deleteVisible: false})}
                />
                <View style={styles.mainContent}>
                    <View style={styles.searchLayout}>
                        <View style={styles.searchForm}>
                            <Form>
                                <Item rounded>
                                    <Input
                                        bordered
                                        placeholder='Search'
                                        onChangeText={(text) => this.onTextChange(text)}
                                        value={this.state.inputText}
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
                                        small
                                        block
                                        bordered
                                        primary
                                        onPress={() => this.itemWordChosen(item.key)}
                                    ><Text style={{color: 'red'}}>{item.key}</Text></Button></View>}
                                />
                            </ScrollView>
                        </View>
                    </View>
                    <View style = {styles.detailLayout}>
                        <Card>
                            <CardItem cardBody style={{height:'80%'}}>
                                <ScrollView>
                                    <Text>{this.props.result}</Text>
                                </ScrollView>
                            </CardItem>
                            <CardItem footer style={{height:'20%'}}>
                                <Item style={{justifyContent: 'center'}}>
                                    <Button
                                        small
                                        success
                                    >
                                        <Text>Insert</Text>
                                    </Button>
                                    <Text>    </Text>
                                    <Button
                                        small
                                        danger
                                        onPress={() => this.setState({deleteVisible: true})}
                                    >
                                        <Text>Delete</Text>
                                    </Button>
                                    <Text>    </Text>
                                    <Button
                                        warning
                                        small
                                    >
                                        <Text>Modify</Text>
                                    </Button>
                                    <Text>    </Text>
                                    <Button
                                        small
                                        primary
                                        onPress={() => this.speakSearchWord()}
                                    >
                                        <Icon name='md-mic'/>
                                    </Button>
                                </Item>
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
        flex: 2.25,
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