import React, { useState } from 'react'
import { registerEmployee } from '../services/employeeService.js'

export default function RegisterEmployee() {
  const [id, setId] = useState('EMP1')
  const [name, setName] = useState('Jane Doe')
  const [email, setEmail] = useState('jane@corp.com')
  const [message, setMessage] = useState('')

  const onSubmit = async (e) => {
    e.preventDefault()
    setMessage('')
    try {
      const res = await registerEmployee({ id, name, email })
      setMessage(`Registered ${res.name} (${res.id})`)
    } catch (err) {
      setMessage(`Error: ${err.data?.message || 'failed'}`)
    }
  }

  return (
    <section>
      <h3>Register Employee</h3>
      <form onSubmit={onSubmit} style={{ display: 'grid', gap: 8, maxWidth: 360 }}>
        <input placeholder="ID" value={id} onChange={e => setId(e.target.value)} />
        <input placeholder="Name" value={name} onChange={e => setName(e.target.value)} />
        <input placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
        <button type="submit">Register</button>
      </form>
      {message && <p>{message}</p>}
    </section>
  )
}
