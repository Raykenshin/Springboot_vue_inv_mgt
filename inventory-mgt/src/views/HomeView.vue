<template>
    <div>
      <h1>库存看板</h1>
      <div v-for="warehouse in warehouses" :key="warehouse.id">
        <p>{{ warehouse.name }} - 已占用: {{ warehouse.usedVolume }}，总容积: {{ warehouse.totalVolume }}</p>
      </div>
    </div>
  </template>
  
  <script lang="ts" setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const warehouses = ref<any[]>([]);
  
  const fetchWarehouses = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/warehouses');
      warehouses.value = response.data;
    } catch (error) {
      console.error('Error fetching warehouses:', error);
    }
  };
  
  onMounted(() => {
    fetchWarehouses();
  });
  </script>