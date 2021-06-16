import React, { useState } from 'react';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import axios from 'axios';

import './Main.scss';
import Input from '../Input/Input';
import Output from '../Output/Output';

const Main = () => {
  const [state, updateState] = useState({ inputVal: {}, outputVal: [] });

  const addValue = e => {
    const valCpy = state.inputVal;
    valCpy[`${e.target.id}`] = e.target.value;
    updateState({
      inputVal: valCpy,
      outputVal: state.outputVal,
    });
  };

  const calculate = e => {
    e.preventDefault();
    let url = 'https://systemy-kolejkowe.herokuapp.com/api/system/get?';
    for (const value in state.inputVal) {
      url += `${value}=${state.inputVal[value]}&`;
    }
    url = url.slice(0, -1);
    axios
      .get(url)
      .then(response => {
        if (response.data.error) {
          toast.error(response.data.error, {
            position: 'bottom-center',
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: false,
            progress: undefined,
          });

          return;
        }

        updateState({
          inputVal: state.inputVal,
          outputVal: response.data,
        });

        console.log('Odpowiedz serwera : ', response.data);
      })
      .catch(err => alert(err));
  };

  return (
    <main className="main">
      <form className="main__content" onSubmit={calculate}>
        <h2 className="main__heading">Dane wejściowe</h2>
        <Input name="lambda" blur={addValue}>
          Liczba zgłoszeń napływających w jednostce czasu <span>(λ)</span>
        </Input>
        <Input name="mu" blur={addValue}>
          Liczba zgłoszeń obsługiwanych w jednostce czasu <span>(μ)</span>
        </Input>
        <Input name="m" blur={addValue}>
          Liczba dostępnych kanałów obsługi <span>(m)</span>
        </Input>
        <button className="main__button" type="submit">
          Oblicz
        </button>
      </form>
      <div className="main__content main__content--results">
        <h2 className="main__heading">Wyniki</h2>
        <div className="main__results">
          <Output val={state.outputVal.probability0}>
            Prawdopodobieństwo, że system jest pusty{' '}
            <span>
              (P<sub>0</sub>)
            </span>
          </Output>
          <Output val={state.outputVal.averageV}>
            Średnia długość kolejki{' '}
            <span>
              (<span>v</span>)
            </span>
          </Output>
          <Output val={state.outputVal.averageN}>
            Średnia liczba zgłoszeń w systemie{' '}
            <span>
              (<span>n</span>)
            </span>
          </Output>
          <Output val={state.outputVal.averageM0}>
            Średnia liczba zgłoszeń w obsłudze{' '}
            <span>
              (<span>m</span>
              <sub>0</sub>)
            </span>
          </Output>
          <Output val={state.outputVal.averageTt}>
            Średni czas oczekiwania zgłoszenia w kolejce{' '}
            <span>
              (<span>t</span>
              <sub>t</sub>)
            </span>
          </Output>
          <Output val={state.outputVal.averageTs}>
            Średni czas przesycania zgłoszenia w systemie{' '}
            <span>
              (<span>t</span>
              <sub>s</sub>)
            </span>
          </Output>
          <Output val={state.outputVal.averageMnz}>
            Średnia liczba nie zajętych kanałów obsługi{' '}
            <span>
              (<span>m</span>
              <sub>nz</sub>)
            </span>
          </Output>
          <div>
            <p className="list-label">Prawdopodobieństwo x zgłoszeń w kolejce</p>
            <select name="probability" id="probability" disabled>
              {state.outputVal.probability?.map((probability, index) => {
                document.getElementById('probability').disabled = false;
                return (
                  <option key={index}>
                    {index + 1} - {probability}
                  </option>
                );
              })}
            </select>
          </div>
        </div>
      </div>
      <ToastContainer
        position="bottom-center"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </main>
  );
};

export default Main;
