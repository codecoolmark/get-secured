import { useState } from "react"

export function Register() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    
    const onLoginFormSubmit = (event) => {
        event.preventDefault()
    
        fetch(new URL("/users", new URL(import.meta.env.VITE_BACKEND_URL)), {
          method: "post",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            username, password
          })
        }).then(response => {
          console.log("User created")
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
            <button type='submit'>Register</button>
        </div>
    </form>
    
}