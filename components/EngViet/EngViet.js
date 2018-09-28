import React from 'react';
import { StyleSheet, Text, View, TextInput, Button, ScrollView, FlatList} from 'react-native';

export default class EngViet extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            inputText: props.data
        }
    }

    onTextChange = function (text) {
        this.setState({
            inputText: text
        })
    };

    render() {
        if (!this.props.isVisible) {
            return (
                <View styke={styles.listOptions}>
                    <Button
                        style = {styles.optionContainer}
                        title = "English - Vietnamese"
                        onPress = {this.props.makeVisible}
                    />
                </View>
            )
        }
        return (
            <View style = {styles.mainContainer}>
                <View style={styles.mainContent}>
                    <View style={styles.searchLayout}>
                        <View style={styles.searchForm}>
                            <TextInput
                                style={{height: '60%', backgroundColor:'#f5f5f5', margin: 5}}
                                placeholder = "Search word"
                                onChangeText={(text) => this.props.onTextChange(text)}
                            />
                            <Button
                                style={{width: '55%', height: '40%'}}
                                onPress={this.props.onSearch}
                                title="Submit"
                                color="#841584"
                            />
                        </View>
                        <View style={styles.listHints}>
                            <ScrollView>
                                <FlatList
                                    data = {
                                        this.props.hints
                                    }
                                    renderItem = {({item}) => <View style={styles.listContainer}><Button
                                        title={item.key}
                                        onPress={() => this.props.onChoose(item.key)}
                                        color = '#00cc00'
                                    /></View>}
                                />
                            </ScrollView>
                        </View>
                    </View>
                    <View style = {styles.detailLayout}>
                        <ScrollView>
                            <Text>{this.props.result}</Text>
                        </ScrollView>
                    </View>
                </View>
                <View style = {styles.footer}>
                    <Button
                        title = "Back"
                        onPress = {this.props.makeVisible}
                        color = '#0099ff'
                    />
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    listOptions: {
        flex: 1,
        justifyContent: 'space-between',
        flexDirection: 'column',
        width: '100%'
    },
    mainContainer: {
        flex: 1,
        justifyContent: 'center',
        backgroundColor: '#e6e6e6'
    },
    buttonContainer: {
        padding: 10,
        justifyContent: 'center',
    },
    optionContainer: {
        padding: 10,
        justifyContent: 'center',
        borderRadius: 15,
        width: '75%'
    },
    searchForm: {
        flex: 2,
        flexDirection: 'column',
        justifyContent: 'center',
    },
    searchLayout: {
        flex: 2,
        flexDirection: 'column',
        justifyContent: 'flex-start',
        padding: 20,
        backgroundColor: '#e6f5ff',
        borderRadius: 15,
        margin: 10
    },
    detailLayout: {
        flex: 7,
        justifyContent: 'center',
        backgroundColor: '#e6f5ff',
        borderRadius: 15,
        margin: 10
    },
    footer: {
        flex: 0.7,
        justifyContent: 'center'
    },
    mainContent: {
        flex: 9,
        justifyContent: 'center',
        flexDirection: 'row'
    },
    listHints: {
        flex: 5,
        backgroundColor: 'white',
        marginTop: 10
    },
    listContainer: {
        margin: 2,
        justifyContent: 'center',
    }
});