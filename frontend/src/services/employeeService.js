import { api } from '../api/client.js'

export function registerEmployee({ id, name, email }) {
  return api('/employees', {
    method: 'POST',
    body: { id, name, email }
  })
}
