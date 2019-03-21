import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * Create a team
   * @param {*} detail the detail of the team
   */
  create (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/boards', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Get a board and everything under it
   * @param {*} boardId the id of the board
   */
  getBoard (boardId) {
    return new Promise((resolve, reject) => {
      axios.get('/boards/' + boardId).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
