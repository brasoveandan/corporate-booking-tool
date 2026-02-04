import React, { useState } from 'react'
import RegisterEmployee from './screens/RegisterEmployee.jsx'
import CreateBooking from './screens/CreateBooking.jsx'
import BookingsList from './screens/BookingsList.jsx'
import HotelAppointment from './screens/HotelAppointment.jsx'

export default function App() {
  const [screen, setScreen] = useState('register')

  return (
    <div style={{ fontFamily: 'system-ui, -apple-system, Segoe UI, Roboto, Helvetica, Arial' }}>
      <header style={{ padding: 12, borderBottom: '1px solid #ddd', marginBottom: 16 }}>
        <strong>Corporate Booking Tool</strong>
        <nav style={{ display: 'inline-flex', gap: 12, marginLeft: 16 }}>
          <button onClick={() => setScreen('register')}>Register Employee</button>
          <button onClick={() => setScreen('createBooking')}>Create Booking</button>
          <button onClick={() => setScreen('listBookings')}>Bookings List</button>
          <button onClick={() => setScreen('hotelAppointment')}>Hotel Appointment</button>
        </nav>
      </header>
      <main style={{ padding: 16 }}>
        {screen === 'register' && <RegisterEmployee />}
        {screen === 'createBooking' && <CreateBooking />}
        {screen === 'listBookings' && <BookingsList />}
        {screen === 'hotelAppointment' && <HotelAppointment />}
      </main>
    </div>
  )
}
