<template>
  <div class="page">
    <PageHeader/>
    <div class="page-body">
      <div class="board-wrapper">
        <div class="board">
          <div class="board-header">
            <div class="board-name board-header-item">{{ board.name }}</div>
            <div class="board-header-divider"></div>
            <div class="team-name board-header-item">
              <span v-if="!board.personal">{{ team.name }}</span>
              <span v-if="board.personal">Personal</span>
            </div>
            <div class="board-header-divider"></div>
            <div class="board-members board-header-item">
              <div class="member" v-for="member in members" v-bind:key="member.id">
                <span>{{ member.shortName }}</span>
              </div>
              <div class="member add-member-toggle">
                <span>
                  <font-awesome-icon icon="user-plus"/>
                </span>
              </div>
            </div>
          </div>
          <div class="board-body">
            <draggable v-model="cardLists" class="list-container" @end="onCardListDragEnded" :options="{handle: '.list-header', animation: 0, scrollSensitivity: 100, touchStartThreshold: 20}">
              <div class="list-wrapper" v-for="cardList in cardLists" v-bind:key="cardList.id">
                <div class="list">
                  <div class="list-header">{{ cardList.name }}</div>
                  <draggable class="cards" v-model="cardList.cards" @end="onCardDragEnded" :options="{draggable: '.card-item', group: 'cards', ghostClass: 'ghost-card',
                    animation: 0, scrollSensitivity: 100, touchStartThreshold: 20}" v-bind:data-list-id="cardList.id">
                    <div class="card-item" v-for="card in cardList.cards" v-bind:key="card.id">
                      <div class="card-title">{{ card.title }}</div>
                    </div>
                  </draggable>
                  <div class="add-card-button">
                    <font-awesome-icon icon="plus"/>
                    <div>Add a card</div>
                  </div>
                </div>
              </div>
              <div class="list-wrapper">
                <font-awesome-icon icon="plus"/>
                <div>Add a list</div>
              </div>
            </draggable>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PageHeader from '@/components/PageHeader.vue'
import draggable from 'vuedraggable'
import boardService from '@/services/boards'
import cardListService from '@/services/card-lists'
import cardService from '@/services/cards'
import notify from '@/utils/notify'

export default {
  name: 'BoardPage',
  props: ['teamId'],
  components: {
    PageHeader,
    draggable
  },
  data () {
    return {
      board: { id: 0, name: '', personal: false },
      cardLists: [/* {id, name, cards} */],
      team: { name: '' },
      members: [/* {id,shortName} */]
    }
  },
  beforeRouteEnter (to, from, next) {
    boardService.getBoard(to.params.boardId).then(data => {
      next(vm => {
        vm.team.name = data.team ? data.team.name : ''
        vm.board.id = data.board.id
        vm.board.name = data.board.name
        vm.board.personal = data.board.personal

        data.members.forEach(member => {
          vm.members.push({
            id: member.id,
            shortName: member.shortName
          })
        })

        data.cardLists.sort((list1, list2) => {
          return list1.position - list2.position
        })

        data.cardLists.forEach(cardList => {
          cardList.cards.sort((card1, card2) => {
            return card1.position - card2.position
          })

          vm.cardLists.push({
            id: cardList.id,
            name: cardList.name,
            cards: cardList.cards
          })
        })
      })
    }).catch(error => {
      notify.error(error.message)
    })
  },
  methods: {
    onCardListDragEnded (event) {
      // Get the latest card list order and send it to the back-end
      const positionChanges = {
        boardId: this.board.id,
        cardListPositions: []
      }

      this.cardLists.forEach((cardList, index) => {
        positionChanges.cardListPositions.push({
          cardListId: cardList.id,
          position: index + 1
        })
      })

      cardListService.changePositions(positionChanges).catch(error => {
        notify.error(error.message)
      })
    },
    onCardDragEnded (event) {
      console.log('card drag ended', event)
      // Get the card list that have card orders changed
      const fromListId = event.from.dataset.listId
      const toListId = event.to.dataset.listId
      const changedListIds = [fromListId]

      if (fromListId !== toListId) {
        changedListIds.push(toListId)
      }

      const positionChanges = {
        boardId: this.board.id,
        cardPositions: []
      }

      changedListIds.forEach(changeListId => {
        const cardList = this.cardLists.filter(cardList => { return cardList.id === parseInt(changeListId) })[0]

        cardList.cards.forEach((card, index) => {
          positionChanges.cardPositions.push({
            cardListId: changeListId,
            cardId: card.id,
            position: index + 1
          })
        })
      })
      cardService.changePositions(positionChanges).catch(error => {
        notify.error(error.message)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.page-body {
  flex-grow: 1;
  position: relative;
  overflow-y: auto;

  .board-wrapper {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;

    .board {
      height: 100%;
      display: flex;
      flex-direction: column;

      .board-header {
        flex: none;
        height: auto;
        overflow: hidden;
        position: relative;
        padding: 8px 4px 8px 8px;

        .board-header-divider {
          float: left;
          border-left: 1px solid #ddd;
          height: 16px;
          margin: 8px 10px;
        }

        .board-header-item {
          float: left;
          height: 32px;
          line-height: 32px;
          margin: 0 4px 0 0;
        }

        .board-name {
          font-size: 18px;
          line-height: 32px;
          padding-left: 4px;
          text-decoration: none;
        }

        .board-members {
          .member {
            display: block;
            float: left;
            height: 30px;
            margin: 0 0 0 -2px;
            border-radius: 50%;
            background-color: #377ef6;
            position: relative;

            span {
              height: 30px;
              line-height: 30px;
              width: 30px;
              text-align: center;
              display: block;
              color: #fff;
            }
          }

          .add-member-toggle {
            margin-left: 5px;
            background-color: #eee;
            cursor: pointer;

            svg {
              font-size: 10px;
              position: absolute;
              top: 9px;
              left: 9px;
              color: #000;
            }
          }
        }
      }

      .board-body {
        position: relative;
        flex-grow: 1;

        .list-container {
          position: absolute;
          top: 0;
          left: 8px;
          right: 0;
          bottom: 0;
          overflow-x: auto;
          overflow-y: hidden;
          white-space: nowrap;
          margin-bottom: 6px;
          padding-bottom: 6px;

          .list-wrapper {
            width: 272px;
            margin: 0 4px;
            height: 100%;
            box-sizing: border-box;
            display: inline-block;
            vertical-align: top;
            white-space: nowrap;

            .list {
              background: #eee;
              border-radius: 3px;
              box-sizing: border-box;
              display: flex;
              flex-direction: column;
              max-height: 100%;
              white-space: normal;
              position: relative;

              .list-header {
                padding: 0.55rem 0.75rem;
                font-weight: 600;
                cursor: pointer;
              }

              .add-card-button {
                padding: 8px 10px;
                color: #888;
                cursor: pointer;
                border-bottom-left-radius: 3px;
                border-bottom-right-radius: 3px;
              }

              .cards {
                overflow-y: auto;
                min-height: 1px;

                .card-item {
                  overflow: hidden;
                  background: #fff;
                  padding: 5px 8px;
                  border-radius: 4px;
                  margin: 0 8px 8px;
                  box-shadow: 0 1px 0 #ccc;
                  cursor: pointer;

                  .card-title {
                    margin: 0;
                  }
                }

                .ghost-card {
                  background-color: #377ef6 !important;
                  color: #377ef6 !important;
                }
              }
            }
          }
        }
      }
    }
  }
}
</style>
