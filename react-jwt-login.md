

### Service Code

```javascript
const baseurl = "http://localhost:5000/users";

export const userLoginApi = async (user) => {
    // call login api with error handling
    const response = await fetch(`${baseurl}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });

    if (response.status >= 200 && response.status < 300) {
        const data = await response.json();
        return data;
    }
    // else{
    //     throw new Error('Invalid credentials');
    // }
}

export const userAccessApi = async () => {
    let authToken = '';
    if (sessionStorage.getItem('auth')) {
        // @ts-ignore
        authToken = sessionStorage.getItem('auth');
    }
    const response = await fetch(`${baseurl}/user`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${authToken}`
        },
    });
    const data = await response.json();
    return data;
}

```
### Component Level

```javascript

import React, { useContext, useState } from 'react';
import Button from 'react-bootstrap/Button';
import { userAccessApi, userLoginApi } from '../../apis/AuthApi';
import { UserContext } from '../../UserContext';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigateToHome = useNavigate();

    const { login, setLogin } = useContext(UserContext);

    const authenticate = (e) => {
        e.preventDefault();
        userLoginApi({ username, password }).then((response) => {
            sessionStorage.setItem('auth', response.jwt);
            sessionStorage.setItem('user', username);
            setLogin(username);
            navigateToHome('/home');
        }).catch((error) => {
            console.error(error);
            alert('Invalid credentials');
        });
    }

    const checkLogin = () => {
        userAccessApi().then((response) => {
            console.log(response);
        });
    }

    return (
        <div>
            <div className='container'>
                <div className='row'>
                    <div className='col-12'>
                        <div className='jumbotron'>
                            <h1 className='display-4'>Login</h1>
                            <p className='lead'>Please login to continue.</p>
                            <hr className='my-4' />
                            <div className='row'>
                                <div className='col-6'>
                                    <form onSubmit={(e) => authenticate(e)}>
                                        <div className='form-group'>
                                            <label htmlFor='username'>Username</label>
                                            <input type='text' className='form-control' id='username' placeholder='Enter username'
                                                onChange={(e) => setUsername(e.target.value)} />
                                        </div>
                                        <div className='form-group'>
                                            <label htmlFor='password'>Password</label>
                                            <input type='password' className='form-control' id='password' placeholder='Enter password'
                                                onChange={(e) => setPassword(e.target.value)} />
                                        </div>
                                        <Button variant='primary' type='submit'>Login</Button>
                                        <Button variant='secondary' onClick={checkLogin} >Test Login</Button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default LoginPage
```
