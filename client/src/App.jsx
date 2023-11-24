import { useState } from 'react'
import bannerImage from './assets/freiheit_stirbt.jpg'
import { Login } from './Login'
import { Register } from './Register'

function App() {
  const [token, setToken] = useState(null)

  return (
    <>
      <h1>Get Secured</h1>

      <details>
        <summary>Register</summary>
        <Register></Register>
      </details>

      <details>
        <summary>Login</summary>
        <Login setToken={setToken}></Login>
      </details>

      <p>Token: {token}</p>

      <img height="150" src={bannerImage} />
    </>
  )
}

export default App
