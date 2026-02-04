import React, { useState } from 'react'
import { createHotelAppointment } from '../services/hotelService.js'

export default function HotelAppointment() {
  const [employeeId, setEmployeeId] = useState('EMP1')
  const [hotelName, setHotelName] = useState('City Hotel')
  const [checkIn, setCheckIn] = useState(new Date().toISOString().substring(0,10))
  const [checkOut, setCheckOut] = useState(new Date().toISOString().substring(0,10))
  const [message, setMessage] = useState('')

  const onSubmit = async (e) => {
    e.preventDefault()
    setMessage('')
    try {
      const res = await createHotelAppointment({ employeeId, hotelName, checkIn, checkOut })
      setMessage(`Created hotel appointment ${res.id} at ${res.hotelName}`)
    } catch (err) {
      setMessage(`Error: ${err.data?.message || 'failed'}`)
    }
  }

  return (
    <section>
      <h3>Hotel Appointment</h3>
      <form onSubmit={onSubmit} style={{ display: 'grid', gap: 8, maxWidth: 360 }}>
        <input placeholder="Employee ID" value={employeeId} onChange={e => setEmployeeId(e.target.value)} />
        <input placeholder="Hotel Name" value={hotelName} onChange={e => setHotelName(e.target.value)} />
        <input type="date" value={checkIn} onChange={e => setCheckIn(e.target.value)} />
        <input type="date" value={checkOut} onChange={e => setCheckOut(e.target.value)} />
        <button type="submit">Create</button>
      </form>
      {message && <p>{message}</p>}
    </section>
  )
}
