/* eslint-disable react-hooks/exhaustive-deps */
import React, {ChangeEvent, useCallback, useEffect, useState} from 'react';
import './App.css';

function App() {
  const [ws, setWs] = useState<WebSocket>();

  useEffect(() => {
    console.log('opening socket on client');
    const socket = new WebSocket('ws://localhost:8080/ws');
    socket.onmessage = message => {
      console.log(message.data);
    };

    setWs(socket);

    return () => {
      console.log('closing socket on client');
      if (ws) {
        ws.close();
      }
    };
  }, []);

  const [outValue, setOutValue] = useState<string>('');

  const setSendValue = useCallback(
    (evt: ChangeEvent<HTMLInputElement>) => {
      setOutValue(evt.target.value);
    },
    [setOutValue]
  );

  const send = useCallback(() => {
    const message = {text: outValue ?? 'hello'};
    ws?.send(JSON.stringify(message));
  }, [ws, outValue]);

  return (
    <div className="App">
      <header className="App-header">
        <input value={outValue} onChange={setSendValue} />
        <br />
        <button onClick={send}>send data over socket</button>
      </header>
    </div>
  );
}

export default App;
