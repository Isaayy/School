import React from 'react';

import './Header.scss'
import pwszImg from '../../assets/images/pwsz.jpg'
import kiImg from '../../assets/images/ki.png'


const Header = () => {
    return (
        <header className="header">
            <img src={pwszImg} className="header__img" alt="PWSZ Logo"/>
            <h1 className="header__heading">Algorytmy i metody optymalizacji II</h1>
            <img src={kiImg} className="header__img" alt="Katedra informatyki logo"/>

        </header>
    )
}

export default Header;