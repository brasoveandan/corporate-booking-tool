import { api } from '../api/client.js'

export function createBooking({ employeeId, resourceType, destination, departureDate, returnDate, travelerCount, costCenterRef, tripPurpose }) {
  return api('/bookings', {
    method: 'POST',
    body: { employeeId, resourceType, destination, departureDate, returnDate, travelerCount, costCenterRef, tripPurpose }
  })
}

export function listBookings(employeeId) {
  return api(`/bookings?employeeId=${encodeURIComponent(employeeId)}`)
}
