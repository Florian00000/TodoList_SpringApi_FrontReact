

export function authHeader() {
  const token = localStorage.getItem('token');
  const headers = {};


  if (token) {
      headers['Authorization'] = 'Bearer ' + token;
  }
  console.log(headers);
  

  return headers;
}
  