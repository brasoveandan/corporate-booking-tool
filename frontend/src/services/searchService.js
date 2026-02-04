import { api } from '../api/client.js'

export function search({ employeeId, resourceType, destination }) {
  return api('/search', {
    method: 'POST',
    body: { employeeId, resourceType, destination }
  })
}
