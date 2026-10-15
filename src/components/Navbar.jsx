import React, { useState, useEffect } from 'react'
import { navLinks } from '../data/data'
import { MenuIcon, XIcon } from 'lucide-react'

const Navbar = ({ onBookTable }) => {
    const [mobileOpen, setMobileOpen] = useState(false)
    const [scrolled, setScrolled] = useState(false)

    useEffect(() => {
        const handleScroll = () => {
            setScrolled(window.scrollY > 10);
        };
        window.addEventListener("scroll", handleScroll);
        return () => window.removeEventListener("scroll", handleScroll);
    }, [])

  return (
    <>
      <nav className={`fixed top-0 z-20 px-auto w-full transition-all duration-300 ${scrolled ? 'bg-white/70 backdrop-blur-md' : 'bg-transparent'}`}>
        <div className='flex items-center justify-between font-medium px-4 py-3 mx-auto max-w-7xl'>
            <a href="/">
                <img src="/assets/logo.svg" alt="logo" />
            </a>

            {/*desktop navigation links*/}
            <div className='hidden md:flex items-center gap-10'>
                {navLinks.map((link)=>(
                    <a key={link.name} href={link.href} className='hover:text-zinc-600'>
                        {link.name}
                    </a>
                ))}
            </div>

            <button 
              type="button"
              onClick={onBookTable}
              className='hidden md:block bg-orange-500 hover:bg-orange-600 text-white px-6 py-3 rounded-full transition cursor-pointer'
            >
                Book a Table
            </button>

            <button
             className='md:hidden bg-zinc-800 text-white p-2 rounded-md aspect-square'
             onClick={() => setMobileOpen(!mobileOpen)}
            >
                <MenuIcon/>
            </button>
        </div>
      </nav>

      {/* mobile navigation links */}
      <div className={`fixed inset-0 z-30 bg-white transition-all duration-300 ease-in-out ${mobileOpen ? 'translate-x-0' : '-translate-x-full'}`}>
        <div className='flex flex-col items-center space-y-6 font-medium pt-20'>
            {navLinks.map((link)=>(
                <a key={link.name} href={link.href} className='text-2xl hover:text-zinc-800 hover:text-orange-500 transition'
                onClick={() => setMobileOpen(false)}
                >
                    {link.name}
                </a>
            ))}

            <button 
              type="button"
              onClick={() => { setMobileOpen(false); onBookTable && onBookTable(); }}
              className='bg-orange-500 hover:bg-orange-600 text-white px-8 py-3 rounded-full transition text-lg'
            >
                Book a Table
            </button>

            <button onClick={() => setMobileOpen(false)}
            className='bg-zinc-800 text-white p-2 rounded-md aspect-square mt-4'>
                <XIcon/>
            </button>
        </div>
      </div>
    </>
  )
}

export default Navbar
