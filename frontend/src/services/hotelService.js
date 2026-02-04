import { api } from '../api/client.js'

export function createHotelAppointment({ employeeId, hotelName, checkIn, checkOut }) {
  return api('/hotel-appointments', {
    method: 'POST',
    body: { employeeId, hotelName, checkIn, checkOut }
  })
}
