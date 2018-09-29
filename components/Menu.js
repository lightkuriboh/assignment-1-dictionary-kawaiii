import React from 'react';
import { StyleSheet, Image} from 'react-native';
import { Container, View, DeckSwiper, Card, CardItem, Thumbnail, Text, Left, Body, Icon } from 'native-base';
import EngViet from './EngViet/EngViet';
import DocumentTrans from './DocumentTrans/DocumentTrans';

export default class Menu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cards: [
                {
                    text: 'English -> Vietnamese',
                    name: 'HaFeng',
                    image: require('../images/engVN.jpg'),
                    id: '1'
                },
                {
                    text: 'Translate Document',
                    name: 'HeFang',
                    image: require('../images/document.png'),
                    id: '2'
                }
            ],
            myAction: '0'
        }
    }

    menuChosen = (selection) => {
        this.setState({
            myAction: selection
        });
    };

    makeInvisible = () => {
        this.setState({
            myAction: '0'
        });
    };

    render() {
        if (this.state.myAction === '1') {
            return (
                <EngViet
                    onTextChange = {(text) => this.props.onTextChange(text)}
                    onSearch = {() => this.props.onSearch()}
                    onChoose = {(word) => this.props.onChoose(word)}
                    makeInvisible = {() => this.makeInvisible()}
                    searchWord = {this.state.searchWord}
                    details = {this.props.details}
                    hints = {this.props.hints}
                    result = {this.props.result}
                />
            )
        } else
            if (this.state.myAction === '2') {
                return (
                    <DocumentTrans
                        makeInvisible = {() => this.makeInvisible()}
                    />
                )
            } else {
                return (
                    <Container>
                        <View>
                            <DeckSwiper
                                dataSource={this.state.cards}
                                renderItem={item =>
                                    <Card style={{elevation: 3}}>
                                        <CardItem button onPress={() => this.menuChosen(item.id)}>
                                            <Left>
                                                <Thumbnail source={item.image}/>
                                                <Body>
                                                <Text>{item.text}</Text>
                                                <Text note>NativeBase</Text>
                                                </Body>
                                            </Left>
                                        </CardItem>
                                        <CardItem cardBody>
                                            <Image style={{height: 300, flex: 1}} source={item.image}/>
                                        </CardItem>
                                        <CardItem>
                                            <Icon name="heart" style={{color: '#ED4A6A'}}/>
                                            <Text>{item.name}</Text>
                                        </CardItem>
                                    </Card>
                                }
                            />
                        </View>
                    </Container>
                )
            }
    }
}
