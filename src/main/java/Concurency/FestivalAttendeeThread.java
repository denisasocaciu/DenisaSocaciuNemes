package Concurency;


public class FestivalAttendeeThread extends Thread {
        private final TicketType ticketType;

        FestivalAttendeeThread(TicketType ticketType, FestivalGate gate) {
            this.ticketType = ticketType;
            gate.addTicket(ticketType);
        }

        @Override
        public void run(){
            System.out.println("new entry with ticket of type:" + ticketType);
        }

    }

