import React from 'react';
import { StyleSheet, Image} from 'react-native';
import { Container, View, DeckSwiper, Card, CardItem, Thumbnail, Text, Left, Body, Icon } from 'native-base';


export default class Menu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cards: [
                {
                    text: 'English -> Vietnamese',
                    name: 'HaFeng',
                    image: require('../images/engVN.jpg'),
                    id: '0'
                },
                {
                    text: 'Translate Document',
                    name: 'HeFang',
                    image: require('../images/document.png'),
                    id: '1'
                }
            ],
            myAction: ''
        }
    }

    menuChosen = (selection) => {
        this.props.makeVisible(selection);
    };

    render() {
        return (
            <Container>
                <View>
                    <DeckSwiper
                        dataSource={this.state.cards}
                        renderItem={item =>
                            <Card style={{ elevation: 3 }}>
                                <CardItem button onPress={() => this.menuChosen(item.id)}>
                                    <Left>
                                        <Thumbnail source={item.image} />
                                        <Body>
                                            <Text>{item.text}</Text>
                                            <Text note>NativeBase</Text>
                                        </Body>
                                    </Left>
                                </CardItem>
                                <CardItem cardBody>
                                    <Image style={{ height: 300, flex: 1 }} source={item.image} />
                                </CardItem>
                                <CardItem>
                                    <Icon name="heart" style={{ color: '#ED4A6A' }} />
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

const styles = StyleSheet.create({

});