import React from 'react';
import { StyleSheet, View, ScrollView, FlatList} from 'react-native';
import { Button, Text, Form, Input, Item, Card, CardItem, Icon } from 'native-base';
import Prompt from 'react-native-input-prompt';
import tts from 'react-native-tts';

export default class EngViet extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            inputText: '',
            myChosen: '',
            details: '',
            hints: [],
            deleteVisible: false,
            hintHeight: '80%',
            detailsHeight: '10%'
        };
    }

    onSearchWord = () => {
        this.setState({
            myChosen: this.state.inputText,
            hintHeight: '0%',
            detailsHeight: '80%'
        });
        this.props.core.greetUser(this.state.myChosen, this.displayResult);
    };

    onTextChange = (text) => {
        this.setState({
            inputText: text
        });

        this.props.core.getHint(text, this.displayHints);

        this.setState({
            hintHeight: '80%',
            detailsHeight: '10%'
        });
    };

    displayHints = (result) => {
        let newJson = result.replace(/([a-zA-Z0-9]+?):/g, '"$1":');
        newJson = newJson.replace(/'/g, '"');
        let data = JSON.parse(newJson);
        this.setState({
            hints: data
        });
    };

    speakSearchWord = () => {
        tts.getInitStatus().then(() => {
            tts.speak(this.state.myChosen);
        }, (err) => {
            if (err.code === 'no_engine') {
                tts.requestInstallEngine();
            }
        });
        tts.stop();
    };

    itemWordChosen = (word) => {
        this.props.core.greetUser(word, this.displayResult);

        this.setState({
            inputText: word,
            myChosen: word,
            hintHeight: '0%',
            detailsHeight: '80%'
        });
    };

    displayResult = (result) => {
        this.setState({
           details: result
        });
    };

    confirmDeleteWord = (word) => {
        this.setState({
            deleteVisible: false
        });
        if (word.toLowerCase() === "y") {
            let myWord = this.state.myChosen;
            let that = this;
            setTimeout(function(){alert("Deleted word {" + that.state.myChosen + "}!");}, 1000);
        }
    };

    hintStyle = function () {
        return {
            height: this.state.hintHeight
        }
    };

    detailsStyle = function () {
        return {
            height: this.state.detailsHeight
        }
    };

    render() {
        return (
            <Card style = {styles.mainContainer}>
                <CardItem cardBody style={{height: '100%'}}>
                    <Prompt
                        title={'Delete This Word'}
                        visible={this.state.deleteVisible}
                        placeholder={this.hintHeight}
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
                                        <Button transparent onPress={this.onSearchWord}>
                                            <Icon name='search' />
                                        </Button>
                                    </Item>
                                </Form>
                            </View>
                        </View>
                        <View style = {styles.detailLayout}>
                            <Card>
                                <CardItem header style={this.hintStyle()}>
                                    <ScrollView>
                                        <FlatList
                                            data = {
                                                this.state.hints
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
                                </CardItem>
                                <CardItem cardBody style={this.detailsStyle()}>
                                    <ScrollView>
                                        <Text>{this.state.details}</Text>
                                    </ScrollView>
                                </CardItem>
                                <CardItem footer style={{height:'10%'}}>
                                    <Item style={{justifyContent: 'center'}}>
                                        <Button
                                            small
                                            primary
                                            onPress={() => this.speakSearchWord()}
                                        >
                                            <Icon name='md-mic'/>
                                        </Button>
                                        {/*<Button*/}
                                            {/*small*/}
                                            {/*danger*/}
                                            {/*onPress={() => this.setState({deleteVisible: true})}*/}
                                        {/*>*/}
                                            {/*<Text>Delete</Text>*/}
                                        {/*</Button>*/}
                                    </Item>
                                </CardItem>
                            </Card>
                        </View>
                    </View>
                </CardItem>
            </Card>
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
        padding: 5
    },
    buttonContainer: {
        padding: 10,
        justifyContent: 'center',
    },
    searchForm: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'flex-start',
    },
    searchLayout: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'flex-start',
        padding: 20,
        margin: 10
    },
    detailLayout: {
        flex: 12,
        justifyContent: 'center',
        borderRadius: 15,
        margin: 10,
        flexDirection: 'column'
    },
    mainContent: {
        flex: 9,
        justifyContent: 'center',
        flexDirection: 'column',
        zIndex: 1
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