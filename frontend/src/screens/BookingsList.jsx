import React, { useState } from 'react'
import { listBookings } from '../services/bookingService.js'

export default function BookingsList() {
  const [employeeId, setEmployeeId] = useState('EMP1')
  const [bookings, setBookings] = useState([])
  const [error, setError] = useState('')

  const onLoad = async () => {
    setError('')
    try {
      const data = await listBookings(employeeId)
      setBookings(data)
    } catch (err) {
      setError(`Error: ${err.data?.message || 'failed'}`)
    }
  }

  return (
    <section>
      <h3>Bookings List</h3>
      <div style={{ display: 'flex', gap: 8, marginBottom: 12 }}>
        <input placeholder="Employee ID" value={employeeId} onChange={e => setEmployeeId(e.target.value)} />
        <button onClick={onLoad}>Load</button>
      </div>
      {error && <p>{error}</p>}
      <ul>
        {bookings.map(b => (
          <li key={b.id} style={{ marginBottom: 12 }}>
            <strong>{b.resourceType}</strong> to <strong>{b.destination}</strong><br/>
            {b.departureDate} → {b.returnDate}<br/>
            {b.travelerCount} traveler(s) | {b.costCenterRef}<br/>
            <em>{b.tripPurpose}</em>
          </li>
        ))}
      </ul>
    </section>
  )
}
