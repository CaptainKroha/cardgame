# Описание WEB API

Используются как Http-запросы, так и WebSocket URL: root_url/ws

{param_name}  в запросе - параметр

> Используется SockJS и STOMP поверх WebSocket

## Типы сообщений, отправляемые сервером в той или иной ситуации

GAME_STARTED     
GAME_STOPPED    
SITUATION_CARD_CHANGED  
ACTION_CARD_DROPPED     
DECK_IS_OVER     
PLAYER_LEFT     
PLAYER_ENTER
DROPPED_CARDS_RETURNED

## Модели, возвращаемые сервером

Room:
String roomId,  
String creatorId,   
Integer cardsPerPlayer,     
Card situationCard,     
Player[] players,      
Card[] situationCards,  
Card[] roleCards,   
Card[] moodCards,   
Card[] actionCards,     
Card[] droppedActionCards,  
Date createdAt,     
Boolean isGameStarted   

Player:
String playerId,    
String login,   
Card roleCard,  
Card moodCard,  
Card[] actionCards

Card:   
String id,  
String content


## Проверка подключения

#Http
`GET /ping`

#Response
pong

## Комната

### Добавление

#Http
`POST /rooms`
```json
{
  "creatorLogin": "Petya",
  "cardsPerPlayer": 1,
  "situationCards": [
    "Situation card 1", 
    "Situation card 2"
  ],
  "roleCards": [
    "Role Card 1",
    "Role Card 2",
    "Role Card 3"
  ],
  "moodCards": [
    "Mood Card 1",
    "Mood Card 2",
    "Mood Card 3"
  ],
  "actionCards": [
    "Action Card 1",
    "Action Card 2",
    "Action Card 3"
  ]
}
```

#Response
roomID

### Получение

#Http
`GET /rooms/roomID`

#Response
Room

## Игрок

### Добавление

Подключение игрока возможно только до ее начала. Для подключения к игре необходимо установить WebSocket соединение, после чего подписаться на следующий топик.

#WebSocket
`SUBSCRIBE /topic/{roomID}`

После подключения по WebSocket необходимо выполнить запрос на добавления игрока.

#Http
`POST /rooms/{roomId}/players`
`Login`

#Response
Player

После добавления игрока все подписанные на топик получать следующее сообщение:

#WebSocketMessage   
```json
{   
    "message":  "PLAYER_ENTER",   
    "body": {   
        "playersCount": 5,     
        "playerLogin": "login"   
    }   
}
```
### Получение

#Http
`GET /rooms/{roomId}/players/{playerId}`

#Response
```
{
    "status": "ROOM_NOT_FOUND"|"PLAYER_NOT_FOUND"|"SUCCESS",
    "player": Player
}
```
### Удаление

Для выхода из игры необходимо разорвать WebSocket  соединение. После выполнить следующий запрос:
#Http
`DELETE /rooms/{roomId}/players/{playerId}`

Все подписанные на топик пользователи получат сообщение:

#WebSocketMessage
```json
{
    "message": "PLAYER_LEFT",
    "body": {
        "playersCount": 5,
        "playerLogin": "login"
    }
}
```
## Смена карты роли/настроения

#Http   
`GET /rooms/{roomId}/players/{playerId}/cards/role/change`  
`GET /rooms/{roomId}/players/{playerId}/cards/mood/change`

#Response   
Player

### Старт игры

#WebSocket
SEND /room/{roomId}/start
``` json
{
    "roomId": roomID
}
```

#WebSocketMessage
```json
{
    "message": "GAME_STARTED",
    "situationCard": {
      "id": "card_id",
      "content": "card_content"
    },
    "players": [
      "Player",
      "Player"
    ]
}
```
### Завершение игры

#WebSocket
`SEND /room/{roomId}/stop`

#WebSocketMessage
```json
{
    "message":"GAME_STOPPED",
    "body": null
}
```

### Смена карты ситуации
#Http
`POST /room/{roomId}/cards/situation/change`

#Response
True/False

#WebSocketMessage
```json
{
  "message": "SITUATION_CARD_CHANGED",
  "body": {
      "newCard": {
        "id": "id",
        "content": "content"
      }
  }
}
```

### Сброс активной карты
#Http
`POST /rooms/{roomId}/players/{playerId}/cards/action/drop/{cardId}`

#Response
True/False

#WebSocketMessage
```json
{
  "message": "ACTION_CARD_DROPPED",
  "body": {
    "droppedCard": {
      "id": "id",
      "content": "content"
    }
  }
}
```

### Взятие активной карты
#Http
`POST /rooms/{roomId}/players/{playerId}/cards/action/draw`

#Response
Player

В случае, если после взятия карты из колоды она кончится, то всем игрокам будет отправлено оповещение
#WebSocketMessage
```json
{
  "message": "DECK_IS_OVER",
  "body": null
}
```

### Перемешать колоду
#Http
`POST /rooms/{roomId}/cards/return-dropped`

#Response
True/False

#WebSocketMessage
```json
{
  "message": "DROPPED_CARDS_RETURNED",
  "body": null
}
```




