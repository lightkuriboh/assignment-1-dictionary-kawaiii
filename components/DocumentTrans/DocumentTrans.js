import React, { Component } from "react";
import { Container, Button, Content, Textarea, Form, Text } from "native-base";
import { StyleSheet, View, ScrollView} from 'react-native';


export default class TextArea extends Component {
    constructor(props) {
        super(props);
        this.state = {
            documentToTrans: '',
            translatedDocument: ''
        };
    }

    onDocumentChange = (text) => {
        this.setState({
            documentToTrans: text
        });
    };


    translate = () => {

        this.setState({translatedDocument: 'Google Translate is a PAID API!'});

    };

    render() {
        return (
            <View style = {styles.mainContainer}>
                <View style={styles.mainContent}>
                    <View style={styles.searchLayout}>
                        <Container>
                            <Content padder>
                                <Form>
                                    <Textarea
                                        rowSpan={5} bordered placeholder="Textarea"
                                        onChangeText={(text) => this.onDocumentChange(text)}
                                    />
                                    <Button block info small
                                            style={{width: '100%', height: '25%'}}
                                            onPress={() => this.translate()}
                                            color="#841584"
                                    >
                                        <Text>Submit</Text>
                                    </Button>
                                </Form>
                            </Content>
                        </Container>
                    </View>
                    <View style={styles.meaningLayout}>
                        <ScrollView>
                            <Text>
                                {this.state.documentToTrans}
                            </Text>
                            <Text>
                                {this.state.translatedDocument}
                            </Text>
                        </ScrollView>
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
};

const styles = StyleSheet.create({
    mainContainer: {
        flex: 1,
        justifyContent: 'center',
        backgroundColor: '#e6e6e6',
        padding: 10
    },
    footer: {
        flex: 0.5,
        flexDirection: 'row',
        justifyContent: 'center',
        zIndex: 2,
        padding: 5
    },
    mainContent: {
        flex: 9,
        justifyContent: 'center',
        flexDirection: 'column',
        zIndex: 1
    },
    searchLayout: {
        flex: 1,
    }
    ,
    meaningLayout: {
        flex: 2,
        backgroundColor: 'white',
        marginTop: 10,
        borderWidth: 1,
        borderColor: 'brown',
    }
});