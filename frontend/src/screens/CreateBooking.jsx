import React, { useState } from 'react'
import { createBooking } from '../services/bookingService.js'

export default function CreateBooking() {
  const [employeeId, setEmployeeId] = useState('EMP1')
  const [resourceType, setResourceType] = useState('FLIGHT')
  const [destination, setDestination] = useState('NYC')
  const [departureDate, setDepartureDate] = useState('2024-11-05T08:00')
  const [returnDate, setReturnDate] = useState('2024-11-08T18:00')
  const [travelerCount, setTravelerCount] = useState(1)
  const [costCenterRef, setCostCenterRef] = useState('CC-456')
  const [tripPurpose, setTripPurpose] = useState('Client meeting')
  const [message, setMessage] = useState('')

  const onSubmit = async (e) => {
    e.preventDefault()
    setMessage('')
    try {
      const res = await createBooking({
        employeeId,
        resourceType,
        destination,
        departureDate: departureDate + ':00',
        returnDate: returnDate + ':00',
        travelerCount: parseInt(travelerCount, 10),
        costCenterRef,
        tripPurpose
      })
      setMessage(`Created booking ${res.id} - ${res.resourceType} to ${res.destination}`)
    } catch (err) {
      setMessage(`Error: ${err.data?.message || 'failed'}`)
    }
  }

  return (
    <section>
      <h3>Create Booking</h3>
      <form onSubmit={onSubmit} style={{ display: 'grid', gap: 8, maxWidth: 400 }}>
        <input placeholder="Employee ID" value={employeeId} onChange={e => setEmployeeId(e.target.value)} />
        <select value={resourceType} onChange={e => setResourceType(e.target.value)}>
          <option value="FLIGHT">FLIGHT</option>
          <option value="HOTEL">HOTEL</option>
        </select>
        <input placeholder="Destination" value={destination} onChange={e => setDestination(e.target.value)} />
        <label>
          Departure
          <input type="datetime-local" value={departureDate} onChange={e => setDepartureDate(e.target.value)} />
        </label>
        <label>
          Return
          <input type="datetime-local" value={returnDate} onChange={e => setReturnDate(e.target.value)} />
        </label>
        <input type="number" min="1" placeholder="Traveler Count" value={travelerCount} onChange={e => setTravelerCount(e.target.value)} />
        <input placeholder="Cost Center Ref" value={costCenterRef} onChange={e => setCostCenterRef(e.target.value)} />
        <input placeholder="Trip Purpose" value={tripPurpose} onChange={e => setTripPurpose(e.target.value)} />
        <button type="submit">Create Booking</button>
      </form>
      {message && <p>{message}</p>}
    </section>
  )
}
