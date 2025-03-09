<template>
    <div>
      <h1>入库操作</h1>
      <select v-model="selectedWarehouse">
        <option value="成品仓库">成品仓库</option>
        <option value="材料仓库">材料仓库</option>
        <option value="临时仓库">临时仓库</option>
        <option value="配料仓">配料仓</option>
      </select>
      <input v-model="productNumber" placeholder="产品号" @input="searchProduct">
      <input v-model="barcode" placeholder="扫码输入国标号" @input="handleBarcodeInput">
      <select v-if="productOptions.length > 0" v-model="selectedProduct">
        <option v-for="option in productOptions" :key="option.id" :value="option">{{ option.productNumber }}</option>
      </select>
      <button v-if="!productExists" @click="createProduct">创建【产品】</button>
      <input v-model="quantity" placeholder="数量">
      <input v-model="barcode" placeholder="扫码输入国标号">
      <button @click="submitInbound">提交入库</button>
      <p>{{ message }}</p>
      <table>
        <thead>
          <tr>
            <th>名称</th>
            <th>产品号</th>
            <th>颜色</th>
            <th>数量</th>
            <th>国标号</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in currentWarehouseProducts" :key="product.id">
            <td>{{ product.name }}</td>
            <td>{{ product.productNumber }}</td>
            <td>{{ product.color }}</td>
            <td>{{ product.quantity }}</td>
            <td>{{ product.nationalStandardNumber }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script lang="ts" setup>
  import { ref } from 'vue';
  import axios from 'axios';
  
  const selectedWarehouse = ref('成品仓库');
  const productNumber = ref('');
  const productOptions = ref<any[]>([]);
  const selectedProduct = ref<any | null>(null);
  const productExists = ref(false);
  const quantity = ref(0);
  const barcode = ref('');
  const message = ref('');
  const currentWarehouseProducts = ref<any[]>([]);
  
  const searchProduct = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/products/productNumber/${productNumber.value}`);
      productOptions.value = response.data;
      productExists.value = productOptions.value.length > 0;
    } catch (error) {
      console.error('Error searching product:', error);
    }
  };
  
 
const handleBarcodeInput = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/products/nationalStandardNumber/${barcode.value}`);
    const product = response.data;
    if (product) {
      // 匹配到已有产品，自动填充产品信息
      productNumber.value = product.productNumber;
      selectedProduct.value = product;
      productExists.value = true;
      // 可以根据需求添加更多自动填充逻辑
    } else {
      // 未匹配到产品，提示是否分配新国标码
      const confirmResult = confirm('未找到对应产品，是否分配新国标码？');
      if (confirmResult) {
        // 实现分配新国标码的逻辑
        // 这里可以调用后端接口来分配新国标码
      }
    }
  } catch (error) {
    console.error('Error searching product by barcode:', error);
  }
}; 

  const createProduct = async () => {
    // 实现创建产品的逻辑
  };
  
  const submitInbound = async () => {
    try {
        if (productExists.value) {
            // 更新已有产品数量
            const productId = selectedProduct.value.id;
            await axios.post(`http://localhost:8080/api/products/updateQuantity/${productId}/${quantity.value}`);
            message.value = `${productNumber.value} 更新成功`;
        } else {
            // 创建新产品并入库
            const newProduct = {
                productNumber: productNumber.value,
                name: '新商品', // 这里需要根据实际情况填写
                color: '无', // 这里需要根据实际情况填写
                quantity: quantity.value,
                nationalStandardNumber: barcode.value,
                warehouse: { name: selectedWarehouse.value }
            };
            await axios.post('http://localhost:8080/api/products', newProduct);
            message.value = `${productNumber.value} 入库成功`;
        }
        // 刷新当前仓库产品列表
        // 这里需要根据实际情况添加根据仓库查询产品的接口
    } catch (error: any) {
        if (error.response && error.response.data.message) {
            message.value = error.response.data.message;
        } else {
            message.value = '入库操作失败，请稍后重试';
        }
        console.error('Error submitting inbound:', error);
    }
};
  </script>