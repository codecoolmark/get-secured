import { useState } from "react"

export function Login({ setToken }) {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    
    const onLoginFormSubmit = (event) => {
        event.preventDefault()
    
        fetch(new URL("/session", new URL(import.meta.env.VITE_BACKEND_URL)), {
          method: "post",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            username, password
          })
        }).then(response => {
          if (response.ok) {
            return response.json()
          }
    
          return null
        }).then(token => {
          if (token != null) {
            setToken(token);
          }
        })
      }
    
    return <form onSubmit={onLoginFormSubmit}>
        <div>
            <label>
            Username <input type='text' name="username" onChange={(event) => setUsername(event.target.value)}/>
            </label>
        </div>
        <div>
            <label>
            Username <input type='password' name="password" onChange={(event) => setPassword(event.target.value)}/>
            </label>
        </div>
        <div>
            <button type='submit'>Login</button>
        </div>
    </form>
    
}