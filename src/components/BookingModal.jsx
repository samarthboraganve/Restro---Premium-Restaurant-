import React, { useState } from 'react';
import { X, Calendar, Clock, Users, CheckCircle, AlertCircle, Loader2 } from 'lucide-react';
import { createReservation } from '../services/api';

const timeSlots = [
  '12:00 PM', '12:30 PM', '01:00 PM', '01:30 PM', '02:00 PM',
  '06:00 PM', '06:30 PM', '07:00 PM', '07:30 PM', '08:00 PM', '08:30 PM', '09:00 PM'
];

const BookingModal = ({ isOpen, onClose }) => {
  const [formData, setFormData] = useState({
    customerName: '',
    customerEmail: '',
    customerPhone: '',
    reservationDate: new Date().toISOString().split('T')[0],
    reservationTime: '07:00 PM',
    guestCount: 2,
    specialRequests: '',
  });

  const [loading, setLoading] = useState(false);
  const [successData, setSuccessData] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');

  if (!isOpen) return null;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: name === 'guestCount' ? parseInt(value) || 1 : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setErrorMessage('');

    try {
      const response = await createReservation(formData);
      setSuccessData(response.data);
    } catch (err) {
      setErrorMessage(err.message || 'Something went wrong. Please check your details and try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleReset = () => {
    setSuccessData(null);
    setErrorMessage('');
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/60 backdrop-blur-sm animate-in fade-in duration-200">
      <div 
        className="relative w-full max-w-lg bg-white rounded-3xl shadow-2xl p-6 md:p-8 max-h-[90vh] overflow-y-auto"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Close Button */}
        <button
          onClick={handleReset}
          className="absolute top-5 right-5 text-gray-400 hover:text-gray-700 bg-gray-100 hover:bg-gray-200 rounded-full p-2 transition"
          aria-label="Close modal"
        >
          <X size={18} />
        </button>

        {successData ? (
          <div className="text-center py-6">
            <div className="size-16 bg-green-100 text-green-600 rounded-full flex items-center justify-center mx-auto mb-4">
              <CheckCircle size={36} />
            </div>
            <h3 className="text-2xl font-bold text-gray-800">Reservation Confirmed!</h3>
            <p className="text-gray-600 mt-2">
              Thank you, <span className="font-semibold text-gray-800">{successData.customerName}</span>! We look forward to hosting you.
            </p>

            <div className="bg-orange-50 border border-orange-200 rounded-2xl p-4 my-6 text-left space-y-2 text-sm text-gray-700">
              <p><span className="font-semibold text-orange-900">Booking ID:</span> #{successData.id}</p>
              <p><span className="font-semibold text-orange-900">Date:</span> {successData.reservationDate}</p>
              <p><span className="font-semibold text-orange-900">Time:</span> {successData.reservationTime}</p>
              <p><span className="font-semibold text-orange-900">Party Size:</span> {successData.guestCount} {successData.guestCount === 1 ? 'Guest' : 'Guests'}</p>
              {successData.specialRequests && (
                <p><span className="font-semibold text-orange-900">Note:</span> {successData.specialRequests}</p>
              )}
            </div>

            <button
              onClick={handleReset}
              className="w-full bg-orange-500 hover:bg-orange-600 text-white font-medium py-3 rounded-full transition shadow-md"
            >
              Done
            </button>
          </div>
        ) : (
          <div>
            <div className="mb-6">
              <span className="text-xs font-bold uppercase tracking-wider text-orange-600 bg-orange-100 px-3 py-1 rounded-full">
                Table Reservation
              </span>
              <h2 className="text-2xl font-bold text-gray-900 mt-2">Book Your Dining Experience</h2>
              <p className="text-gray-500 text-sm mt-1">
                Fill in the details below to reserve your table instantly.
              </p>
            </div>

            {errorMessage && (
              <div className="mb-4 p-3 bg-red-50 border border-red-200 text-red-700 rounded-xl flex items-center gap-2 text-sm">
                <AlertCircle size={18} className="shrink-0" />
                <span>{errorMessage}</span>
              </div>
            )}

            <form onSubmit={handleSubmit} className="space-y-4">
              <div>
                <label className="block text-xs font-semibold text-gray-700 uppercase mb-1">
                  Full Name *
                </label>
                <input
                  type="text"
                  name="customerName"
                  required
                  value={formData.customerName}
                  onChange={handleChange}
                  placeholder="e.g. Sofia Martinez"
                  className="w-full border border-gray-300 rounded-xl px-3.5 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                />
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-3">
                <div>
                  <label className="block text-xs font-semibold text-gray-700 uppercase mb-1">
                    Email *
                  </label>
                  <input
                    type="email"
                    name="customerEmail"
                    required
                    value={formData.customerEmail}
                    onChange={handleChange}
                    placeholder="you@example.com"
                    className="w-full border border-gray-300 rounded-xl px-3.5 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                  />
                </div>
                <div>
                  <label className="block text-xs font-semibold text-gray-700 uppercase mb-1">
                    Phone *
                  </label>
                  <input
                    type="tel"
                    name="customerPhone"
                    required
                    value={formData.customerPhone}
                    onChange={handleChange}
                    placeholder="+1 555-0199"
                    className="w-full border border-gray-300 rounded-xl px-3.5 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                  />
                </div>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-3 gap-3">
                <div>
                  <label className="block text-xs font-semibold text-gray-700 uppercase mb-1 flex items-center gap-1">
                    <Calendar size={13} /> Date *
                  </label>
                  <input
                    type="date"
                    name="reservationDate"
                    required
                    min={new Date().toISOString().split('T')[0]}
                    value={formData.reservationDate}
                    onChange={handleChange}
                    className="w-full border border-gray-300 rounded-xl px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                  />
                </div>
                <div>
                  <label className="block text-xs font-semibold text-gray-700 uppercase mb-1 flex items-center gap-1">
                    <Clock size={13} /> Time *
                  </label>
                  <select
                    name="reservationTime"
                    value={formData.reservationTime}
                    onChange={handleChange}
                    className="w-full border border-gray-300 rounded-xl px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition bg-white"
                  >
                    {timeSlots.map((slot) => (
                      <option key={slot} value={slot}>{slot}</option>
                    ))}
                  </select>
                </div>
                <div>
                  <label className="block text-xs font-semibold text-gray-700 uppercase mb-1 flex items-center gap-1">
                    <Users size={13} /> Guests *
                  </label>
                  <select
                    name="guestCount"
                    value={formData.guestCount}
                    onChange={handleChange}
                    className="w-full border border-gray-300 rounded-xl px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition bg-white"
                  >
                    {[1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 15].map((count) => (
                      <option key={count} value={count}>
                        {count} {count === 1 ? 'Guest' : 'Guests'}
                      </option>
                    ))}
                  </select>
                </div>
              </div>

              <div>
                <label className="block text-xs font-semibold text-gray-700 uppercase mb-1">
                  Special Requests (Optional)
                </label>
                <textarea
                  name="specialRequests"
                  rows={2}
                  value={formData.specialRequests}
                  onChange={handleChange}
                  placeholder="Dietary requirements, high chair, anniversary celebration..."
                  className="w-full border border-gray-300 rounded-xl px-3.5 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition resize-none"
                />
              </div>

              <button
                type="submit"
                disabled={loading}
                className="w-full bg-orange-500 hover:bg-orange-600 disabled:bg-orange-300 text-white font-medium py-3 rounded-full transition shadow-md flex items-center justify-center gap-2 mt-2"
              >
                {loading ? (
                  <>
                    <Loader2 size={18} className="animate-spin" />
                    <span>Confirming Reservation...</span>
                  </>
                ) : (
                  <span>Confirm Table Reservation</span>
                )}
              </button>
            </form>
          </div>
        )}
      </div>
    </div>
  );
};

export default BookingModal;
